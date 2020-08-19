package com.oocl.reservationsystem.controller;

import com.oocl.reservationsystem.dto.OrderDTO;
import com.oocl.reservationsystem.entity.Orders;
import com.oocl.reservationsystem.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderService){
        this.orderServiceImpl=orderService;
    }

    @PostMapping(value = "/order")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Orders addOrder(@RequestBody OrderDTO orderDTO){
        return orderServiceImpl.addOrder(orderDTO);
    }
}
