package io.viascom.springbootcrudexample.service;

import io.viascom.springbootcrudexample.model.CategoryEntity;
import io.viascom.springbootcrudexample.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryEntity> loadAll() {
        log.info("Executing find all games ...");
        return repository.findAll();
    }

    public Optional<CategoryEntity> loadOne(Long gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public CategoryEntity update(CategoryEntity updatedGame) {
        log.info("Executing update game with id " + updatedGame.getId() + " ...");
        return repository.save(updatedGame);
    }

    public void delete(Long gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        repository.deleteById(gameId);
    }

}
