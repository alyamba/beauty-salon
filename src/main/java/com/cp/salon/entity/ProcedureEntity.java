package com.cp.salon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProcedureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO maybe generationType = AUTO
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private CategoryEntity category;

    private Integer slotSize;

    private Float price;
}
