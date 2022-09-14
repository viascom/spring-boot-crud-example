package io.viascom.springbootcrudexample.service;

import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GameService {

    private final GameRepository repository;

    GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<GameEntity> loadAllGames() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    public Optional<GameEntity> loadGame(Long gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public GameEntity updateGame(GameEntity updatedGame) {
        log.info("Executing update game with id " + updatedGame.getId() + " ...");
        return repository.save(updatedGame);
    }

    public void deleteGame(Long gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        repository.deleteById(gameId);
    }

}
