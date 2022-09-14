package io.viascom.springbootcrudexample.repository;

import io.viascom.springbootcrudexample.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAll();
}
