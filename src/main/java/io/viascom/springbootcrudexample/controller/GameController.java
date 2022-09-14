package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@Tag(name = "Games", description = "Game management endpoints")
public class GameController {

    private final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
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
    GameEntity loadOne(@PathVariable Long id) {
        return gameService.loadOne(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found!"));
    }

    @Operation(
            summary = "Create a new game",
            description = "Creates a new game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PostMapping
    GameEntity create(@RequestBody GameEntity entity, @PathVariable Long id) {
        return gameService.create(entity);
    }

    @Operation(
            summary = "Update an existing game",
            description = "Updates one specific and existing game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PutMapping("/{id}")
    GameEntity update(@RequestBody GameEntity updatedGameEntity, @PathVariable Long id) {
        return gameService.update(updatedGameEntity);
    }

    @Operation(
            summary = "Delete an existing game",
            description = "Deletes one specific and existing game in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        gameService.delete(id);
    }

}
