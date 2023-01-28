package com.chill.chillbbs.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chill.chillbbs.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author Jarviz
 */
@Service
public class TokenServiceImpl implements TokenService {
    Algorithm algorithm = Algorithm.HMAC256("jarviz");

    @Override
    public String getToken(String username) {
        String token = "";
        try {
            token = JWT.create().withExpiresAt(Instant.now().plusMillis(1000L * 60 * 60 * 60 * 24 * 60))
                    .withClaim("username", username)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public String getUsername(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("jarviz");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
