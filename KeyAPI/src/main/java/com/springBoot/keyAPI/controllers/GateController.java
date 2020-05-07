package com.springBoot.keyAPI.controllers;

import com.google.gson.Gson;
import com.springBoot.keyAPI.model.*;
import com.springBoot.keyAPI.model.dto.CompanyDTO;
import com.springBoot.keyAPI.model.dto.KeyRequest;
import com.springBoot.keyAPI.model.dto.OfficeDTO;
import com.springBoot.keyAPI.model.dto.auth.JwtAuthenticationResponse;
import com.springBoot.keyAPI.security.JwtTokenProvider;
import com.springBoot.keyAPI.services.KeyService;
import com.springBoot.keyAPI.services.OfficeService;
import com.springBoot.keyAPI.util.KeyPairGeneratorRSA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Comparator;
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

    @GetMapping("/gateSelectionMenu")
    public List<OfficeDTO> getGateSelectionMenu(){
        Comparator<OfficeDTO> companyIdComparator = Comparator.comparing(o -> o.getCompany().getCompanyId());
        Comparator<OfficeDTO> officeIdComparator = Comparator.comparing(OfficeDTO::getOfficeId);
        Comparator<OfficeDTO> compare = companyIdComparator.thenComparing(officeIdComparator);

        return this.officeService.getAll().stream().map(
                office ->  new OfficeDTO(office.getOfficeId(),office.getAddress(),
                        new CompanyDTO(office.getCompany().getCompanyId(),office.getCompany().getName())))
                .sorted(compare)
                .collect(Collectors.toList());
    }

    @PostMapping("/persistKey")
    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
    public Boolean persistPublicKey(@RequestBody KeyRequest req) {
        Key key = new Key();
        try{
            byte[] bytePubKey = Base64.getDecoder().decode(req.getPublicKey());
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(new X509EncodedKeySpec(bytePubKey));
            key.setUserId(req.getUserId());
            key.setOfficeId(req.getOfficeId());
            key.setDeviceId(req.getDeviceId());
            key.setPublicKey(publicKey);
        }
        catch (Exception e){
            return false;
        }
        System.out.println(key.toString());

        return keyService.add(key);
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
        System.out.println(key.toString());
        PublicKey publicKey = key.getPublicKey();

        KeyValidation response = tokenProvider.validateGateToken(token,publicKey);
        return ResponseEntity.ok(response);
    }


}
