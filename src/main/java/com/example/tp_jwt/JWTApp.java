package com.example.tp_jwt;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.SQLOutput;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class JWTApp {
    public static String createTwtToken (String secret) {
        //create JWT token
        SecretKey secretKey = new SecretKeySpec(Base64.getUrlDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
        Map<String, String> claims = new HashMap<>();
        claims.put("email", "kcarmene07@gmail.com");
        Instant now = Instant.now();
        System.out.println(now.toString());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setId("19641968")
                .setExpiration(Date.from(now.plus(10l, ChronoUnit.MINUTES)))
                .setSubject("jwt authentification")
                .setClaims(claims)
                .signWith(secretKey);
        String jwtToken = jwtBuilder.compact();
        System.out.println(jwtToken);
        return jwtToken;
    }
    //parse and valide the jwt token
    public static void parseValidateToken (String secret, String token) {
        SecretKey secretKey = new SecretKeySpec(Base64.getUrlDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
        Jws <Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        System.out.println(claimsJws.toString());

    }
    public static void main(String[] args) {
        String secret = "sfmUh_jm6BSGBoU_8kgypmbcU8qrK-OEk7KW45EXbO4"; //1512 bits soit 32 caracteres une fois le header et le payload afficher on remplace le secret par la signatura afin quelle puisse generer le token
        String jwtToken = createTwtToken(secret);
        parseValidateToken(secret,jwtToken);
    }
}

