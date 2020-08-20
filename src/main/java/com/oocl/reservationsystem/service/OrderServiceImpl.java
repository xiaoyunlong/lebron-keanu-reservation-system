package com.oocl.reservationsystem.service;

import com.oocl.reservationsystem.dto.OrderDTO;
import com.oocl.reservationsystem.entity.Orders;
import com.oocl.reservationsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository repository;

    public Orders addOrder(OrderDTO orderDTO) {
        Orders orders=new Orders();
        orders=toOrderEntity(orderDTO,orders);
        return repository.save(orders);
    }

    @Override
    public Orders toOrderEntity(OrderDTO orderDTO,Orders orders) {
        orders.setParkingLot(orderDTO.getParkingLot());
        orders.setParkingPosition(orderDTO.getParkingPosition());
        orders.setParkTime(new Date());
        orders.setStartTime(orderDTO.getStartTime());
        System.out.println(orderDTO.getStartTime());
        orders.setEndTime(orderDTO.getEndTime());
        return orders;
    }
}
