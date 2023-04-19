package com.cp.salon.controller;

import com.cp.salon.entity.MasterEntity;
import com.cp.salon.entity.OrderEntity;
import com.cp.salon.entity.ProcedureEntity;
import com.cp.salon.entity.UserEntity;
import com.cp.salon.service.AuthService;
import com.cp.salon.service.OrderService;
import com.cp.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody MasterEntity master,
                                 @RequestBody Date date,
                                 @RequestBody UserEntity user,
                                 @RequestBody ProcedureEntity procedure,
                                 @RequestParam String token){
        try {
            userService.auth(token);
            orderService.add(user.getId(), procedure.getId(), master.getId(), date);
            return ResponseEntity.ok().body("Запись успешно создана");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity getAllByUser(@RequestBody UserEntity user, @RequestParam String token){
        try {
            userService.auth(token);
            return ResponseEntity.ok().body(orderService.getAllByUser(user.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity confirm(@RequestBody OrderEntity order, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
            orderService.confirm(order);
            return ResponseEntity.ok().body("Заявка подтверждена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
