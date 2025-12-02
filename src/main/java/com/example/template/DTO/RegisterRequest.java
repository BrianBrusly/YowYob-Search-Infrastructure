/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.Size
 *  org.hibernate.validator.constraints.Email
 *  org.reactivestreams.Publisher
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package com.example.template.DTO;

import com.example.template.Entity.User;
import com.example.template.Repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RegisterRequest
implements UserRepository {
    @NotBlank(message="Name is required")
    private @NotBlank(message="Name is required") String name;
    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private @Email(message="Invalid email format") @NotBlank(message="Email is required") String email;
    @NotBlank(message="Password is required")
    @Size(min=8, message="Password must be at least 8 characters")
    private @NotBlank(message="Password is required") @Size(min=8, message="Password must be at least 8 characters") String password;

    @Override
    public Mono<User> findByEmail(String email) {
        return null;
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return null;
    }

    @Override
    public Flux<User> findByNameContainingIgnoreCase(String name) {
        return null;
    }

    @Override
    public Mono<Void> deleteByEmail(String email) {
        return null;
    }

    public <S extends User> Mono<S> save(S entity) {
        return null;
    }

    public <S extends User> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public <S extends User> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }

    public Mono<User> findById(Long aLong) {
        return null;
    }

    public Mono<User> findById(Publisher<Long> id) {
        return null;
    }

    public Mono<Boolean> existsById(Long aLong) {
        return null;
    }

    public Mono<Boolean> existsById(Publisher<Long> id) {
        return null;
    }

    public Flux<User> findAll() {
        return null;
    }

    public Flux<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    public Flux<User> findAllById(Publisher<Long> idStream) {
        return null;
    }

    public Mono<Long> count() {
        return null;
    }

    public Mono<Void> deleteById(Long aLong) {
        return null;
    }

    public Mono<Void> deleteById(Publisher<Long> id) {
        return null;
    }

    public Mono<Void> delete(User entity) {
        return null;
    }

    public Mono<Void> deleteAllById(Iterable<? extends Long> longs) {
        return null;
    }

    public Mono<Void> deleteAll(Iterable<? extends User> entities) {
        return null;
    }

    public Mono<Void> deleteAll(Publisher<? extends User> entityStream) {
        return null;
    }

    public Mono<Void> deleteAll() {
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RegisterRequest)) {
            return false;
        }
        RegisterRequest other = (RegisterRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        return !(this$password == null ? other$password != null : !this$password.equals(other$password));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RegisterRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "RegisterRequest(name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
