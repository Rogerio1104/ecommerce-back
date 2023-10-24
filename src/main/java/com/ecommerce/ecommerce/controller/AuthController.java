package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.AuthenticationDTO;
import com.ecommerce.ecommerce.model.LoginResponseDTO;
import com.ecommerce.ecommerce.model.UsuarioEntity;
import com.ecommerce.ecommerce.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

    @Autowired
    public AuthenticationManager auth_manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value="/auth")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth    = this.auth_manager.authenticate(usernamePassword);
        var token   = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping(value="/auth/verifytoken")
    public boolean verifyToken(@RequestParam String token){
        String _token = tokenService.validateToken(token);
        return _token == "" ? false : true;
    }
}
