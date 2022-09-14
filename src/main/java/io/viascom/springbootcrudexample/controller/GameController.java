package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.Operation;
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
            description = "Loads all games from database."
    )
    @GetMapping
    List<GameEntity> loadAllGames() {
        return gameService.loadAll();
    }

    @Operation(
            summary = "Get one specific game",
            description = "Loads one specific game from database."
    )
    @GetMapping("/{id}")
    GameEntity loadOneGame(@PathVariable Long id) {
        return gameService.loadOne(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found!"));
    }

    @Operation(
            summary = "Update an existing game",
            description = "Updates one specific and existing game in database."
    )
    @PutMapping("/{id}")
    GameEntity updateGame(@RequestBody GameEntity updatedGameEntity, @PathVariable Long id) {
        return gameService.update(updatedGameEntity);
    }

    @Operation(
            summary = "Delete an existing game",
            description = "Deletes one specific and existing game in database."
    )
    @DeleteMapping("/{id}")
    void deleteGame(@PathVariable Long id) {
        gameService.delete(id);
    }

}
