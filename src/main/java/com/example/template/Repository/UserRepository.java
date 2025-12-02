/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.repository.reactive.ReactiveCrudRepository
 *  org.springframework.stereotype.Repository
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package com.example.template.Repository;

import com.example.template.Entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository
extends ReactiveCrudRepository<User, Long> {
    public Mono<User> findByEmail(String var1);

    public Mono<Boolean> existsByEmail(String var1);

    public Flux<User> findByNameContainingIgnoreCase(String var1);

    public Mono<Void> deleteByEmail(String var1);
}
