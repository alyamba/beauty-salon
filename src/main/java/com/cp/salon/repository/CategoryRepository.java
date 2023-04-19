package com.cp.salon.repository;

import com.cp.salon.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(Long id);

    List<CategoryEntity> findAll();
}
