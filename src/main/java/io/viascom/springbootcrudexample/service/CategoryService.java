package io.viascom.springbootcrudexample.service;

import io.viascom.springbootcrudexample.exception.CategoryNotFoundException;
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

    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> loadAll() {
        log.info("Executing find all categories ...");
        return categoryRepository.findAll();
    }

    public List<CategoryEntity> loadAllByName(String categoryName) {
        log.info("Executing find categories by name '" + categoryName + "' ...");
        return categoryRepository.findAllByName(categoryName);
    }

    public CategoryEntity loadOne(UUID categoryId) {
        log.info("Executing find category with id " + categoryId + " ...");
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + categoryId));
    }

    public CategoryEntity create(CategoryEntity category) {
        log.info("Executing create category with id " + category.getId() + " ...");
        return categoryRepository.save(category);
    }

    public CategoryEntity update(CategoryEntity updatedCategory) {
        log.info("Executing update category with id " + updatedCategory.getId() + " ...");
        val categoryId = updatedCategory.getId();
        categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + categoryId));
        return categoryRepository.save(updatedCategory);
    }

    public void delete(UUID categoryId) {
        log.info("Executing delete category with id " + categoryId + " ...");
        categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + categoryId));
        categoryRepository.deleteById(categoryId);
    }

}
