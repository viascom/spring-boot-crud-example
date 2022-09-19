package io.viascom.springbootcrudexample.service;

import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class GameService {

    private final GameRepository repository;

    GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<GameEntity> loadAll() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    public GameEntity loadOne(UUID gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
    }

    public GameEntity create(GameEntity game) {
        log.info("Executing create game with id " + game.getId() + " ...");
        return repository.save(game);
    }

    public GameEntity update(GameEntity updatedGame) {
        log.info("Executing update game with id " + updatedGame.getId() + " ...");
        val gameId = updatedGame.getId();
        repository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
        return repository.save(updatedGame);
    }

    public void delete(UUID gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        repository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
        repository.deleteById(gameId);
    }

}
