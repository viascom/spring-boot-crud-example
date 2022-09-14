package io.viascom.springbootcrudexample.service;

import io.viascom.springbootcrudexample.model.CategoryEntity;
import io.viascom.springbootcrudexample.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<CategoryEntity> loadOne(UUID gameId) {
        log.info("Executing find game with id " + gameId + " ...");
        return repository.findById(gameId);
    }

    public CategoryEntity create(CategoryEntity category) {
        log.info("Executing create category with id " + category.getId() + " ...");
        return repository.save(category);
    }

    public CategoryEntity update(CategoryEntity updatedGame) {
        log.info("Executing update game with id " + updatedGame.getId() + " ...");
        return repository.save(updatedGame);
    }

    public void delete(UUID gameId) {
        log.info("Executing delete game with id " + gameId + " ...");
        repository.deleteById(gameId);
    }

}
