package io.viascom.springbootcrudexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.viascom.springbootcrudexample.model.CategoryEntity;
import io.viascom.springbootcrudexample.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
            description = "Loads all categories from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping
    List<CategoryEntity> loadAll(@RequestParam(value = "name", required = false) String categoryName) {

        if(categoryName != null) {
            return categoryService.loadAllByName(categoryName);
        }

        return categoryService.loadAll();
    }

    @Operation(
            summary = "Get one specific category",
            description = "Loads one specific category from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/{id}")
    CategoryEntity loadOne(@PathVariable UUID id) {
        return categoryService.loadOne(id);
    }

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    CategoryEntity create(@RequestBody CategoryEntity entity) {
        return categoryService.create(entity);
    }

    @Operation(
            summary = "Update an existing category",
            description = "Updates one specific and existing category in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    CategoryEntity update(@RequestBody CategoryEntity updatedEntity, @PathVariable UUID id) {
        return categoryService.update(updatedEntity);
    }

    @Operation(
            summary = "Delete an existing category",
            description = "Deletes one specific and existing category in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        categoryService.delete(id);
    }

}
