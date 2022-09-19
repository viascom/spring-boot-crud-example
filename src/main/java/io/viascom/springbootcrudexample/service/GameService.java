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

    private final GameRepository gameRepository;

    GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameEntity> loadAll() {
        log.info("Executing find all games ...");
        return gameRepository.findAll();
    }

    public List<GameEntity> loadAllByName(String gameName) {
        log.info("Executing find games by name '" + gameName + "' ...");
        return gameRepository.findAllByName(gameName);
    }

    public GameEntity loadOne(UUID gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
    }

    public GameEntity create(GameEntity game) {
        log.info("Executing create game with id " + game.getId() + " ...");
        return gameRepository.save(game);
    }

    public GameEntity update(GameEntity updatedGame) {
        log.info("Executing update game with id " + updatedGame.getId() + " ...");
        val gameId = updatedGame.getId();
        gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
        return gameRepository.save(updatedGame);
    }

    public void delete(UUID gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
        gameRepository.deleteById(gameId);
    }

}
