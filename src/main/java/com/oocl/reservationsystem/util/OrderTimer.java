package com.oocl.reservationsystem.util;


import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.enums.order.OrderStatus;
import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
import com.oocl.reservationsystem.service.orderservice.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderTimer {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Scheduled(fixedRate = 3000)
    public void cancelTimeoutOrder() {
        List<Order> orderList = orderService.findOrdersListByStatus(OrderStatus.NOT_USED);
        for (Order order : orderList) {
            if ((new Date().getTime() - order.getStartTime().getTime()) >= 0) {
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.save(order);
            }
        }
    }

}
