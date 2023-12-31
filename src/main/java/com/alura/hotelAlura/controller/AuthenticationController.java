package com.alura.hotelAlura.controller;

import com.alura.hotelAlura.infra.security.DatosJWTToken;
import com.alura.hotelAlura.infra.security.TokenService;
import com.alura.hotelAlura.model.users.AuthenticationUserData;
import com.alura.hotelAlura.model.users.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuthentication(@RequestBody @Valid AuthenticationUserData authenticationUserData) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(authenticationUserData.login(),
                authenticationUserData.password());

        var authenticateUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) authenticateUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
