package com.cp.salon.repository;

import com.cp.salon.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByEmailAndToken(String email, String token);

    UserEntity findByToken(String token);
}
