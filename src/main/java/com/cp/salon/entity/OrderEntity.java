package com.cp.salon.entity;

import com.cp.salon.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private MasterEntity master;

    @ManyToOne
    private ProcedureEntity procedure;

    private OrderStatus status;

    private Date date;
}

