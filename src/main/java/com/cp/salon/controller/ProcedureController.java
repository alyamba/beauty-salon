package com.cp.salon.controller;

import com.cp.salon.entity.CategoryEntity;
import com.cp.salon.entity.ProcedureEntity;
import com.cp.salon.exeption.UserExeption;
import com.cp.salon.model.Procedure;
import com.cp.salon.service.AuthService;
import com.cp.salon.service.ProcedureService;
import com.cp.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {
    @Autowired
    ProcedureService procedureService;

    @Autowired
    AuthService authService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ProcedureEntity procedure, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        procedureService.add(procedure);
        return ResponseEntity.ok("Процедура добавлена");
    }

    @GetMapping("/getAllByCategory")
    public ResponseEntity getAllByCategory(@RequestBody CategoryEntity category){
        List<Procedure> procedures = procedureService.getByCategoryId(category.getId());
        return ResponseEntity.ok().body(procedures);
    }

    @PutMapping("/remove")
    public ResponseEntity remove(@RequestBody ProcedureEntity procedure, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        procedureService.remove(procedure);
        return ResponseEntity.ok("Процедура удалена");
    }

    @PatchMapping("/edit")
    public ResponseEntity edit(@RequestBody ProcedureEntity procedure, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        procedureService.edit(procedure);
        return ResponseEntity.ok("Процедура успешна изменена");
    }
}
