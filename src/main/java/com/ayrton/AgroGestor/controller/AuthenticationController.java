package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.infra.security.TokenService;
import com.ayrton.AgroGestor.repository.UserRepository;
import com.ayrton.AgroGestor.user.AuthenticationDTO;
import com.ayrton.AgroGestor.user.LoginResponseDTO;
import com.ayrton.AgroGestor.user.RegisterDTO;
import com.ayrton.AgroGestor.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        // criptografar a senha para guardar na base de dados(Hash)
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        // autenticar o usuario e senha
        var auth = this.authenticationManager.authenticate(usernamePassword);

        //retornar token
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        // Verificar se o usuario ja existe
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
