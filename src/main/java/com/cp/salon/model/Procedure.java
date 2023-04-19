package com.cp.salon.model;

import com.cp.salon.entity.ProcedureEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Procedure {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private Integer slotSize;
    private Float price;

    public static Procedure toModel(ProcedureEntity procedureEntity){
        Procedure procedure = new Procedure();
        procedure.setId(procedureEntity.getId());
        procedure.setName(procedureEntity.getName());
        procedure.setDescription(procedureEntity.getDescription());
        procedure.setCategory(Category.toModel(procedureEntity.getCategory()));
        procedure.setSlotSize(procedure.slotSize);
        procedure.setPrice(procedureEntity.getPrice());
        return procedure;
    }
}
