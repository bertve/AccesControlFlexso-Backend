package com.springBoot.keyAPI.controllers;

import com.google.gson.Gson;
import com.springBoot.keyAPI.model.Key;
import com.springBoot.keyAPI.model.KeyId;
import com.springBoot.keyAPI.model.KeyValidation;
import com.springBoot.keyAPI.model.User;
import com.springBoot.keyAPI.model.dto.KeyRequest;
import com.springBoot.keyAPI.model.dto.auth.JwtAuthenticationResponse;
import com.springBoot.keyAPI.security.JwtTokenProvider;
import com.springBoot.keyAPI.services.KeyService;
import com.springBoot.keyAPI.services.OfficeService;
import com.springBoot.keyAPI.util.KeyPairGeneratorRSA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gate")
public class GateController {
    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private KeyService keyService;

    @GetMapping("/{officeId}/authorized")
    public List<Long> getAuthorizedIdsByOfficeId(@PathVariable("officeId")Long id){
        return this.officeService.getById(id).getUsers().stream().map(User::getUserId).collect(Collectors.toList());
    }

    @GetMapping("/{officeId}/info")
    public String getAdressByOfficeId(@PathVariable("officeId")Long id){
        return this.officeService.getById(id).getAddress().addressString();
    }


    @PostMapping("/genKey")
    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> generateKey(@RequestBody KeyRequest req){
        KeyPairGeneratorRSA256 generator;
        try{
            generator = new KeyPairGeneratorRSA256();

            String jwt = tokenProvider.generateGateToken(req,generator.getPrivateKey());
            Key key = new Key();
            key.setUserId(req.getUserId());
            key.setOfficeId(req.getOfficeId());
            key.setDeviceId(req.getDeviceId());
            key.setPublicKey(generator.getPublicKey());
            keyService.add(key);

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<KeyValidation> validateKey(@PathVariable("token")String token,@RequestBody String keyIdString){
        //python json string conversion
        String formatedString = keyIdString.substring(1,keyIdString.length()-1)
                .replace("\\","")
                .replace("\"","'");
        Gson gson = new Gson();
        KeyId keyId = gson.fromJson(formatedString,KeyId.class);

        Key key = keyService.getById(keyId);
        if(key == null){
            return ResponseEntity.ok(new KeyValidation(false,"key does not exist",""));
        }
        PublicKey publicKey = key.getPublicKey();
        KeyValidation response = tokenProvider.validateGateToken(token,publicKey);
        return ResponseEntity.ok(response);
    }

}
