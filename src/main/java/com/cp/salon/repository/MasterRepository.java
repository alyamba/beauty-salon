package com.cp.salon.repository;

import com.cp.salon.entity.MasterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MasterRepository extends CrudRepository<MasterEntity, Long> {
    MasterEntity findFirstById(Long id);

    Optional<MasterEntity> findOneById(Long id);
}
