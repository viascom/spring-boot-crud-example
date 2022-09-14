package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Games", description = "Game management endpoints")
public class GameController {

    private final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    List<GameEntity> loadAllGames() {
        return gameService.loadAll();
    }

    @GetMapping("/games/{id}")
    GameEntity loadOneGame(@PathVariable Long id) {
        return gameService.loadOne(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found!"));
    }

    @PutMapping("/games/{id}")
    GameEntity updateGame(@RequestBody GameEntity updatedGameEntity, @PathVariable Long id) {
        return gameService.update(updatedGameEntity);
    }

    @DeleteMapping("/games/{id}")
    void deleteGame(@PathVariable Long id) {
        gameService.delete(id);
    }

}
