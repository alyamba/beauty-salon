package com.cp.salon.service;

import com.cp.salon.entity.MasterEntity;
import com.cp.salon.entity.OrderEntity;
import com.cp.salon.entity.ProcedureEntity;
import com.cp.salon.entity.UserEntity;
import com.cp.salon.exeption.UserExeption;
import com.cp.salon.model.Order;
import com.cp.salon.model.OrderStatus;
import com.cp.salon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    MasterService masterService;

    @Autowired
    ProcedureService procedureService;

    public void add(Long userId, Long procedureId, Long masterId, Date date) throws Exception {
        UserEntity user = userService.getById(userId);
        MasterEntity master = masterService.getById(masterId);
        ProcedureEntity procedure = procedureService.getById(procedureId);
        masterService.disableSlot(masterId, date, procedure.getSlotSize());
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setMaster(master);
        order.setProcedure(procedure);
        order.setDate(date);
        order.setStatus(OrderStatus.CREATE);
        orderRepository.save(order);
    }

    public void confirm(OrderEntity order){
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();
        orderEntity.setStatus(OrderStatus.DONE);
        orderRepository.save(orderEntity);
    }

    public List<Order> getAllByUser(Long userId) throws UserExeption {
        UserEntity user = userService.getById(userId);
        List<OrderEntity> orderEntities = orderRepository.findAllByUser(user);
        List<Order> orders = new ArrayList<Order>();
        for (OrderEntity order: orderEntities){
            orders.add(Order.toModel(order));
        }
        return orders;
    }
}
