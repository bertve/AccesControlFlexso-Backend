package com.springBoot.keyAPI.security;

import com.google.gson.Gson;
import com.springBoot.keyAPI.model.KeyValidation;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public KeyValidation validateGateToken(String token, PublicKey publicKey) {
        String message = "Token is verified";
        boolean succes = false;
        String data = "";

        try {
            data = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody().getSubject();
            succes = true;
        } catch (SignatureException ex) {
            message = "Invalid JWT signature";
        } catch (MalformedJwtException ex) {
            message = "Invalid JWT token";
        } catch (ExpiredJwtException ex) {
            message = "Expired JWT token";
        } catch (UnsupportedJwtException ex) {
            message = "Unsupported JWT token";
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }

        return new KeyValidation(succes,message,data);
    }

}