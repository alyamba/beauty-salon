package com.cp.salon.repository;

import com.cp.salon.entity.ProcedureEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcedureRepository extends CrudRepository<ProcedureEntity, Long> {
    List<ProcedureEntity> findAllByCategory_Id(Long categoryId);

    ProcedureEntity findFirstById(Long procedureId);
}
