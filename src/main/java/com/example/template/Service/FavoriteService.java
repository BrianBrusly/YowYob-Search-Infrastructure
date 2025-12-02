/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package com.example.template.Service;

import com.example.template.DTO.Favorite.FavoriteRequest;
import com.example.template.DTO.Favorite.FavoriteResponse;
import com.example.template.Entity.Favorite;
import com.example.template.Repository.FavoriteRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public Mono<FavoriteResponse> addFavorite(Long userId, FavoriteRequest request) {
        return this.favoriteRepository.existsByUserIdAndItemId(userId, request.getItemId()).flatMap(exists -> {
            if (exists.booleanValue()) {
                return Mono.error((Throwable)new RuntimeException("Item already in favorites"));
            }
            Favorite favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setItemId(request.getItemId());
            favorite.setItemType(request.getItemType());
            favorite.setTitle(request.getTitle());
            favorite.setUrl(request.getUrl());
            favorite.setNotes(request.getNotes());
            favorite.setAddedAt(LocalDateTime.now());
            return this.favoriteRepository.save(favorite).map(this::toResponse);
        });
    }

    public Flux<FavoriteResponse> getUserFavorites(Long userId) {
        return this.favoriteRepository.findByUserIdOrderByAddedAtDesc(userId).map(this::toResponse);
    }

    public Flux<FavoriteResponse> getUserFavoritesByType(Long userId, String itemType) {
        return this.favoriteRepository.findByUserIdAndItemTypeOrderByAddedAtDesc(userId, itemType).map(this::toResponse);
    }

    public Mono<Void> deleteFavorite(Long favoriteId, Long userId) {
        return this.favoriteRepository.findById(favoriteId).flatMap(favorite -> {
            if (!favorite.getUserId().equals(userId)) {
                return Mono.error((Throwable)new RuntimeException("Unauthorized"));
            }
            return this.favoriteRepository.deleteById(favoriteId);
        });
    }

    public Mono<Void> deleteFavoriteByItemId(Long userId, String itemId) {
        return this.favoriteRepository.findByUserIdAndItemId(userId, itemId).flatMap(favorite -> this.favoriteRepository.deleteById(favorite.getId()));
    }

    public Mono<Void> clearAllFavorites(Long userId) {
        return this.favoriteRepository.deleteByUserId(userId);
    }

    public Mono<FavoriteResponse> updateFavoriteNotes(Long favoriteId, Long userId, String notes) {
        return this.favoriteRepository.findById(favoriteId).flatMap(favorite -> {
            if (!favorite.getUserId().equals(userId)) {
                return Mono.error((Throwable)new RuntimeException("Unauthorized"));
            }
            favorite.setNotes(notes);
            return this.favoriteRepository.save(favorite);
        }).map(this::toResponse);
    }

    public Mono<Long> countUserFavorites(Long userId) {
        return this.favoriteRepository.countByUserId(userId);
    }

    private FavoriteResponse toResponse(Favorite favorite) {
        return new FavoriteResponse(favorite.getId(), favorite.getItemId(), favorite.getItemType(), favorite.getTitle(), favorite.getUrl(), favorite.getAddedAt(), favorite.getNotes());
    }

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }
}
