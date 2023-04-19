package com.cp.salon.controller;

import com.cp.salon.entity.UserEntity;
import com.cp.salon.exeption.UserExeption;
import com.cp.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity userEntity){
        try{
            return ResponseEntity.ok(userService.login(userEntity));
        }
        catch (UserExeption e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
            catch(Exception e){
            return  ResponseEntity.badRequest().body("Непредвиденная ошибка");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserEntity userEntity){
        try{
            userService.register(userEntity);
            return ResponseEntity.ok("Вы успешно зарегистрировались");
        }
        catch (UserExeption e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e){
            return  ResponseEntity.badRequest().body("Непредвиденная ошибка");
        }
    }

}
