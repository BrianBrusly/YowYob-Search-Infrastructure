/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.springframework.security.core.annotation.AuthenticationPrincipal
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PatchMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package com.example.template.Controller;

import com.example.template.DTO.Favorite.FavoriteRequest;
import com.example.template.DTO.Favorite.FavoriteResponse;
import com.example.template.Service.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value={"/api/favorites"})
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public Mono<FavoriteResponse> addFavorite(@AuthenticationPrincipal Long userId, @Valid @RequestBody FavoriteRequest request) {
        return this.favoriteService.addFavorite(userId, request);
    }

    @GetMapping
    public Flux<FavoriteResponse> getFavorites(@AuthenticationPrincipal Long userId) {
        return this.favoriteService.getUserFavorites(userId);
    }

    @GetMapping(value={"/type/{itemType}"})
    public Flux<FavoriteResponse> getFavoritesByType(@AuthenticationPrincipal Long userId, @PathVariable String itemType) {
        return this.favoriteService.getUserFavoritesByType(userId, itemType);
    }

    @GetMapping(value={"/count"})
    public Mono<Long> countFavorites(@AuthenticationPrincipal Long userId) {
        return this.favoriteService.countUserFavorites(userId);
    }

    @DeleteMapping(value={"/{favoriteId}"})
    public Mono<Void> deleteFavorite(@AuthenticationPrincipal Long userId, @PathVariable Long favoriteId) {
        return this.favoriteService.deleteFavorite(favoriteId, userId);
    }

    @DeleteMapping(value={"/item/{itemId}"})
    public Mono<Void> deleteFavoriteByItemId(@AuthenticationPrincipal Long userId, @PathVariable String itemId) {
        return this.favoriteService.deleteFavoriteByItemId(userId, itemId);
    }

    @DeleteMapping
    public Mono<Void> clearAllFavorites(@AuthenticationPrincipal Long userId) {
        return this.favoriteService.clearAllFavorites(userId);
    }

    @PatchMapping(value={"/{favoriteId}/notes"})
    public Mono<FavoriteResponse> updateNotes(@AuthenticationPrincipal Long userId, @PathVariable Long favoriteId, @RequestBody String notes) {
        return this.favoriteService.updateFavoriteNotes(favoriteId, userId, notes);
    }

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
}
