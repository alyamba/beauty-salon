package com.cp.salon.model;

import com.cp.salon.entity.OrderEntity;
import com.cp.salon.entity.SlotEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private User user;
    private Master master;
    private Procedure procedure;
    private Date date;
    private OrderStatus status;
    public static Order toModel(OrderEntity orderEntity){
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setUser(User.toModel(orderEntity.getUser()));
        order.setMaster(Master.toModel(orderEntity.getMaster()));
        order.setProcedure(Procedure.toModel(orderEntity.getProcedure()));
        order.setDate(orderEntity.getDate());
        order.setStatus(orderEntity.getStatus());
        return order;
    }
}
