package com.cp.salon.controller;

import com.cp.salon.entity.SlotEntity;
import com.cp.salon.service.AuthService;
import com.cp.salon.service.SlotService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    SlotService slotService;

    @Autowired
    AuthService authService;

    @PostMapping("/createSlots")
    public ResponseEntity createSlots(@RequestBody SlotEntity slotEntity, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        slotService.createSlotForNextWeek(slotEntity.getDate());
        return ResponseEntity.ok("Слоты успешно созданы");
    }
}
