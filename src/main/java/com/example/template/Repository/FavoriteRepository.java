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

import com.example.template.Entity.Favorite;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoriteRepository
extends ReactiveCrudRepository<Favorite, Long> {
    public Flux<Favorite> findByUserIdOrderByAddedAtDesc(Long var1);

    public Flux<Favorite> findByUserIdAndItemTypeOrderByAddedAtDesc(Long var1, String var2);

    public Mono<Boolean> existsByUserIdAndItemId(Long var1, String var2);

    public Mono<Favorite> findByUserIdAndItemId(Long var1, String var2);

    public Mono<Void> deleteByUserId(Long var1);

    public Mono<Long> countByUserId(Long var1);
}
