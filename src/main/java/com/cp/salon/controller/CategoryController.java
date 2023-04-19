package com.cp.salon.controller;


import com.cp.salon.entity.CategoryEntity;
import com.cp.salon.service.AuthService;
import com.cp.salon.service.CategotyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategotyService categotyService;

    @Autowired
    AuthService authService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CategoryEntity categoryEntity, @RequestParam String adminPassword) throws Exception {
        authService.authAdmin(adminPassword);
        categotyService.add(categoryEntity);
        return ResponseEntity.ok("Категория добавлена");
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
         return ResponseEntity.ok().body(categotyService.getAll());
    }
}
