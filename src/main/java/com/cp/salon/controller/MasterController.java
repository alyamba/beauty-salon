package com.cp.salon.controller;

import com.cp.salon.entity.MasterEntity;
import com.cp.salon.exeption.MasterExeption;
import com.cp.salon.model.Id;
import com.cp.salon.model.MasterSlot;
import com.cp.salon.model.Procedure;
import com.cp.salon.service.AuthService;
import com.cp.salon.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    MasterService masterService;

    @Autowired
    AuthService authService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody MasterEntity master, @RequestParam String adminPassword) {
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        masterService.add(master);
        return ResponseEntity.ok("Мастер успешно добавлен");
    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestBody Id id) throws MasterExeption {
        MasterEntity master = masterService.getById(id.getId());
        return ResponseEntity.ok().body(master);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(masterService.getAll());
    }

    @PutMapping("/remove")
    public ResponseEntity remove(@RequestBody MasterEntity master, @RequestParam String adminPassword) {
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        masterService.remove(master);
        return ResponseEntity.ok("Мастер успешно удален");
    }

    @PatchMapping("/edit")
    public ResponseEntity edit(@RequestBody MasterEntity master, @RequestParam String adminPassword) {
        try {
            authService.authAdmin(adminPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        masterService.edit(master);
        return ResponseEntity.ok("Информация о мастере успешно изменена");
    }

    @GetMapping("/getMasterProcedure")
    public ResponseEntity getMasterProcedure(@RequestBody MasterEntity master){
        List<Procedure> procedures = null;
        try {
            procedures = masterService.getProcedureByMaster(master);
            return ResponseEntity.ok().body(procedures);
        } catch (MasterExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/disableSlot")
    public ResponseEntity disableSlot(@RequestBody MasterSlot slotData){
        try {
            masterService.disableSlot(slotData.getMasterId(), slotData.getStartDate(), slotData.getSlotSize());
            return ResponseEntity.ok("Слот успешно забронирован");
        } catch (MasterExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addSlot")
    public ResponseEntity addSlot(@RequestBody MasterSlot slotData, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
            masterService.addSlot(slotData.getMasterId(), slotData.getStartDate(), slotData.getSlotSize());
            return ResponseEntity.ok("Слот успешно добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addWeekSlot")
    public ResponseEntity addWeekSlot(@RequestBody MasterSlot slotData, @RequestParam String adminPassword){
        try {
            authService.authAdmin(adminPassword);
            masterService.addWeekSlot(slotData.getMasterId(), slotData.getStartDate() );
            return ResponseEntity.ok("Слоты успешно добавлены");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
