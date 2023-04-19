package com.cp.salon.repository;


import com.cp.salon.entity.OrderEntity;
import com.cp.salon.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUser(UserEntity user);
}
