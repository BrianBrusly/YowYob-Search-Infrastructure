/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package com.example.template.Controller;

import com.example.template.DTO.RegisterRequest;
import com.example.template.Entity.User;
import com.example.template.Service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value={"/api/users"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value={"/get"})
    public Flux<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(value={"/{id}"})
    public Mono<User> getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping(value={"/create"})
    public Mono<User> createUser(@RequestBody RegisterRequest userRequest) {
        return this.userService.createUser(userRequest);
    }

    @DeleteMapping(value={"/{id}"})
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return this.userService.deleteUser(id);
    }
}
