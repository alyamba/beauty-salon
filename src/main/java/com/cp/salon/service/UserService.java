package com.cp.salon.service;

import com.cp.salon.entity.UserEntity;
import com.cp.salon.exeption.UserExeption;
import com.cp.salon.model.User;
import com.cp.salon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public User login(UserEntity userEntity) throws UserExeption {
        UserEntity user = userRepository.findByEmail(userEntity.getEmail());
        if(user == null) {
            throw new UserExeption("Пользователь с таким email не найден");
        }
        if(!user.getPassword().equals(userEntity.getPassword())) {
            throw new UserExeption("Неверный пароль");
        }
        user.setToken(authService.generateToken(user));
        userRepository.save(user);
        return User.toModel(user);
    }

    public User register(UserEntity userEntity) throws UserExeption {
        if(userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new UserExeption("Пользователь с таким email уже существует");
        }
        userEntity.setToken(authService.generateToken(userEntity));
        userRepository.save(userEntity);
        return User.toModel(userRepository.save(userEntity));
    }

    public boolean auth(String token) throws UserExeption {
        UserEntity user = userRepository.findByToken(token);
        if(user == null){
            return false;
        }
        return true;
    }

    public User findByToken(String token) throws UserExeption {
        UserEntity user = userRepository.findByToken(token);
        if(user == null){
            throw new UserExeption("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public User getInfo(String token) throws UserExeption {
        UserEntity user = userRepository.findByToken(token);
        if(user == null){
            throw new UserExeption("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public UserEntity getById(Long id) throws UserExeption {
        UserEntity user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new UserExeption("Пользователь не найден");
        }
        return user;
    }

}
