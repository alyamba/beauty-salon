package com.cp.salon.entity;

import com.cp.salon.exeption.MasterExeption;
import com.cp.salon.model.Slot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private List<ProcedureEntity> procedure;

    @ManyToMany
    private List<SlotEntity> slots;

    public void removeSlot(SlotEntity slot) throws MasterExeption {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH-mm-ss");
        for(SlotEntity slotEntity: slots){
            if(slotEntity.getDate().getTime() == (slot.getDate().getTime())){
                slots.remove(slotEntity);
                return;
            }
        }
        throw new MasterExeption("Слот уже занят");
    }

    public void addSlot(SlotEntity slot) throws MasterExeption {
        for(SlotEntity slotEntity: slots){
            if(slotEntity.getDate().equals(slot.getDate())){
                throw new MasterExeption("Слот уже занят");
            }
        }
        slots.add(slot);
    }
}
