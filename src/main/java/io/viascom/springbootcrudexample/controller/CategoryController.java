package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.exception.GameNotFoundException;
import io.viascom.springbootcrudexample.model.CategoryEntity;
import io.viascom.springbootcrudexample.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Category management endpoints")
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Get all categories",
            description = "Loads all categories from database."
    )
    @GetMapping
    List<CategoryEntity> loadAllCategories() {
        return categoryService.loadAll();
    }

    @Operation(
            summary = "Get one specific category",
            description = "Loads one specific category from database."
    )
    @GetMapping("/{id}")
    CategoryEntity loadOneGame(@PathVariable Long id) {
        return categoryService.loadOne(id)
                .orElseThrow(() -> new GameNotFoundException("Category with id " + id + " not found!"));
    }

    @Operation(
            summary = "Update an existing category",
            description = "Updates one specific and existing category in database."
    )
    @PutMapping("/{id}")
    CategoryEntity updateGame(@RequestBody CategoryEntity updatedEntity, @PathVariable Long id) {
        return categoryService.update(updatedEntity);
    }

    @Operation(
            summary = "Delete an existing category",
            description = "Deletes one specific and existing category in database."
    )
    @DeleteMapping("/{id}")
    void deleteGame(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
