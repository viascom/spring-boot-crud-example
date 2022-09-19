package io.viascom.springbootcrudexample.repository;

import io.viascom.springbootcrudexample.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findAll();
    List<CategoryEntity> findAllByName(String name);
}
