package com.alura.hotelAlura.infra.security;

import com.alura.hotelAlura.model.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apisecret;


    public String generateToken(User user) {

        try {
            var algorithm = Algorithm.HMAC256(apisecret);
            return JWT.create().withIssuer("hotelAlura").withSubject(user.getLogin()).withClaim("id", user.getId()).sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apisecret);
            verifier = JWT.require(algorithm).withIssuer("hotelAlura").build().verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException e) {
            System.out.println(e.toString());
        }

        if (verifier.getSubject() == null) {
            throw new RuntimeException("Invalid Verifier");
        }
        return verifier.getSubject();
    }
}
