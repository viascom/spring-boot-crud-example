package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.service.CategoryService;
import io.viascom.springbootcrudexample.service.GameService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/games")
@Tag(name = "Games", description = "Game management endpoints")
public class GameController {

    private final GameService gameService;
    private final CategoryService categoryService;

    GameController(GameService gameService, CategoryService categoryService) {
        this.gameService = gameService;
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Get all games",
            description = "Loads all games from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping
    List<GameEntity> loadAll(@RequestParam(value = "name", required = false) String gameName) {

        if(gameName != null) {
            return gameService.loadAllByName(gameName);
        }

        return gameService.loadAll();
    }

    @Operation(
            summary = "Get one specific game by id",
            description = "Loads one specific game by id from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    GameEntity loadOne(@PathVariable UUID id) {
        return gameService.loadOne(id);
    }

    @Operation(
            summary = "Create a new game",
            description = "Creates a new game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    GameEntity create(@RequestBody GameEntity entity) {
        entity.setCategory(categoryService.loadOne(entity.getCategoryId()));
        return gameService.create(entity);
    }

    @Operation(
            summary = "Update an existing game",
            description = "Updates one specific and existing game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    GameEntity update(@RequestBody GameEntity updatedGameEntity, @PathVariable UUID id) {
        return gameService.update(updatedGameEntity);
    }

    @Operation(
            summary = "Delete an existing game",
            description = "Deletes one specific and existing game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        gameService.delete(id);
    }

}
