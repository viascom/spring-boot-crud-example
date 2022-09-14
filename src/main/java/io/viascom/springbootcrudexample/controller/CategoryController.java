package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.CategoryEntity;
import io.viascom.springbootcrudexample.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Category", description = "Category management endpoints")
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    List<CategoryEntity> loadAllCategories() {
        return categoryService.loadAll();
    }

    @GetMapping("/categories/{id}")
    CategoryEntity loadOneGame(@PathVariable Long id) {
        return categoryService.loadOne(id)
                .orElseThrow(() -> new GameNotFoundException("Category with id " + id + " not found!"));
    }

    @PutMapping("/categories/{id}")
    CategoryEntity updateGame(@RequestBody CategoryEntity updatedEntity, @PathVariable Long id) {
        return categoryService.update(updatedEntity);
    }

    @DeleteMapping("/categories/{id}")
    void deleteGame(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
