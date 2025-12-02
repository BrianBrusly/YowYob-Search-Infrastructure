/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 *  org.springframework.stereotype.Service
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package com.example.template.Service;

import com.example.template.DTO.RegisterRequest;
import com.example.template.Entity.User;
import com.example.template.Repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Mono<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public Mono<User> createUser(RegisterRequest request) {
        return this.userRepository.existsByEmail(request.getEmail()).flatMap(exists -> {
            if (Boolean.TRUE.equals(exists)) {
                return Mono.error((Throwable)new RuntimeException("Email already exists"));
            }
            User user = new User();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordEncoder.encode((CharSequence)request.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            return this.userRepository.save(user);
        });
    }

    public Mono<Void> deleteUser(Long id) {
        return this.userRepository.deleteById(id);
    }
}
