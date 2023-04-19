package com.cp.salon.model;

import com.cp.salon.entity.SlotEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Slot {
    private Long id;
    private Date date;

    public static Slot toModel(SlotEntity slotEntity){
        Slot slot = new Slot();
        slot.setId(slotEntity.getId());
        slot.setDate(slotEntity.getDate());
        return slot;
    }

}
