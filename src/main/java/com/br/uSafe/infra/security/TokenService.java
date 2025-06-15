// package com.br.uSafe.infra.security;

// import java.time.Instant;
// import java.time.temporal.ChronoUnit;

// import org.springframework.stereotype.Service;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.br.uSafe.model.User;

// @Service
// public class TokenService {
//     private String secret = "your-secret";
//     public String generateToken(User user) {
//        try{
//             Algorithm algorithm = Algorithm.HMAC256(secret);
//             String token = JWT.create()
//                 .withIssuer("api")
//                 .withSubject(user.getEmail())
//                 .withClaim("role", user.getRole().name())
//                 .sign(algorithm);
//        } catch (Exception e) {
//            throw new RuntimeException("Error generating token", e);
//        }
//     }

//     private Instant getExpiration() {
//         return Instant.now().plus(2, ChronoUnit.HOURS);
//     }
// }
