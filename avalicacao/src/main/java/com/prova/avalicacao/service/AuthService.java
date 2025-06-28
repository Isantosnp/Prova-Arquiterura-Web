package com.prova.avalicacao.service;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prova.avalicacao.repository.UserRepository;
import com.prova.avalicacao.model.User;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     @param username 
     @param password 
     @throws BadCredentialsException
     */
    public String authenticateUserAndGenerateToken(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("Credenciais inválidas: Usuário não encontrado.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Credenciais inválidas: Senha incorreta.");
        }

        return jwtService.generateToken(user.getUsername(), user.getRole());
    }
}
 
 