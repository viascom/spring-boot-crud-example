package io.viascom.springbootcrudexample.service;

import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.CategoryEntity;
import io.viascom.springbootcrudexample.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryEntity> loadAll() {
        log.info("Executing find all categories ...");
        return repository.findAll();
    }

    public CategoryEntity loadOne(UUID categoryId) {
        log.info("Executing find category with id " + categoryId + " ...");
        return repository.findById(categoryId).orElseThrow(() -> new GameNotFoundException("Category not found with id " + categoryId));
    }

    public CategoryEntity create(CategoryEntity category) {
        log.info("Executing create category with id " + category.getId() + " ...");
        return repository.save(category);
    }

    public CategoryEntity update(CategoryEntity updatedCategory) {
        log.info("Executing update category with id " + updatedCategory.getId() + " ...");
        val categoryId = updatedCategory.getId();
        repository.findById(categoryId).orElseThrow(() -> new GameNotFoundException("Category not found with id " + categoryId));
        return repository.save(updatedCategory);
    }

    public void delete(UUID categoryId) {
        log.info("Executing delete category with id " + categoryId + " ...");
        repository.findById(categoryId).orElseThrow(() -> new GameNotFoundException("Category not found with id " + categoryId));
        repository.deleteById(categoryId);
    }

}
