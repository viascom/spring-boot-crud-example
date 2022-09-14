package io.viascom.springbootcrudexample.controller;

import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private final GameService service;

    GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/games")
    List<GameEntity> loadAllGames() {
        return service.loadAllGames();
    }

    @GetMapping("/games/{id}")
    GameEntity loadOneGame(@PathVariable Long id) {
        return service.loadGame(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found!"));
    }

    @PutMapping("/games/{id}")
    GameEntity updateGame(@RequestBody GameEntity updatedGameEntity, @PathVariable Long id) {
        return service.updateGame(updatedGameEntity);
    }

    @DeleteMapping("/games/{id}")
    void deleteGame(@PathVariable Long id) {
        service.deleteGame(id);
    }

}
