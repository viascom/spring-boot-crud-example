package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.service.CategoryService;
import io.viascom.springbootcrudexample.service.GameService;
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
    List<GameEntity> loadAll() {
        return gameService.loadAll();
    }

    @Operation(
            summary = "Get one specific game",
            description = "Loads one specific game from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    GameEntity loadOne(@PathVariable UUID id) {
        return gameService.loadOne(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found!"));
    }

    @Operation(
            summary = "Create a new game",
            description = "Creates a new game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PostMapping
    GameEntity create(@RequestBody GameEntity entity) {
        entity.setCategory(categoryService.loadOne(entity.getCategoryId()).orElseThrow());
        return gameService.create(entity);
    }

    @Operation(
            summary = "Update an existing game",
            description = "Updates one specific and existing game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PutMapping("/{id}")
    GameEntity update(@RequestBody GameEntity updatedGameEntity, @PathVariable UUID id) {
        return gameService.update(updatedGameEntity);
    }

    @Operation(
            summary = "Delete an existing game",
            description = "Deletes one specific and existing game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        gameService.delete(id);
    }

}
