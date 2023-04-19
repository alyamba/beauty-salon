package com.cp.salon.repository;

import com.cp.salon.entity.SlotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SlotRepository extends CrudRepository<SlotEntity, Long> {
    SlotEntity findFirstByDate(Date date);
}
