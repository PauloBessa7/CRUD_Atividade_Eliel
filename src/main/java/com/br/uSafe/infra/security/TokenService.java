package com.br.uSafe.infra.security;

import java.time.Instant;
import java.util.Date;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.uSafe.model.User;

@Service
public class TokenService {
    private String secret = "your-secret";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm)
                    .withIssuer("api")
                    .build()
                    .verify(token)
                    .getSubject();
            return "Token is valid";
        } catch (JWTVerificationException e) {
            return "Invalid token: " + e.getMessage();
        }
    }

    private Date getExpirationDate() {
        return Date.from(Instant.now().plus(2, ChronoUnit.HOURS));
    }
}
