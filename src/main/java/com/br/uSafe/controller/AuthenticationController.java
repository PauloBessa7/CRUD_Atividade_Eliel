package com.br.uSafe.controller;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.uSafe.DTO.AuthenticationDTO;
import com.br.uSafe.DTO.RegisterDTO;
import com.br.uSafe.model.User;
import com.br.uSafe.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
     
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@Validated @RequestBody RegisterDTO data) {
        if(this.userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        String encodePassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.email(), encodePassword, data.role(), data.name());

        this.userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.getId())
                        .toUri();
        return ResponseEntity.created(location).body(user);
    }
}
