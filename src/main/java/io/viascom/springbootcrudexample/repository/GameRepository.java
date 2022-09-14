package io.viascom.springbootcrudexample.repository;

import io.viascom.springbootcrudexample.model.GameEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<GameEntity, Long> {
    List<GameEntity> findAll();
}
