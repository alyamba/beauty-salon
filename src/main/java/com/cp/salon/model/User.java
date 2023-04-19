package com.cp.salon.model;

import com.cp.salon.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String token;
    private String name;
    private String surname;

    public static User toModel(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setToken(userEntity.getToken());
        return user;
    }
}
