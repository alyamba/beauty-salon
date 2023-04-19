package com.cp.salon.model;

import com.cp.salon.entity.MasterEntity;
import com.cp.salon.entity.ProcedureEntity;
import com.cp.salon.entity.SlotEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Master {
    private Long id;
    private String name;
    private List<Procedure> procedures;
    private List<Slot> slots;

    public static Master toModel(MasterEntity masterEntity){
        Master master = new Master();
        master.setId(masterEntity.getId());
        master.setName(master.getName());

        for(ProcedureEntity procedureEntity: masterEntity.getProcedure()){
            master.procedures.add(Procedure.toModel(procedureEntity));
        }

        for(SlotEntity slot: masterEntity.getSlots()){
            master.slots.add(Slot.toModel(slot));
        }

        return master;
    }
}
