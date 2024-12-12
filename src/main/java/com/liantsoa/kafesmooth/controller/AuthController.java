package com.liantsoa.kafesmooth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import com.liantsoa.kafesmooth.model.User;
import com.liantsoa.kafesmooth.model.Role;

import com.liantsoa.kafesmooth.repository.RoleRepository;
import com.liantsoa.kafesmooth.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_EMPLOYEE")
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
        user.getRoles().add(userRole);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
