package com.oocl.reservationsystem.service;

import com.oocl.reservationsystem.dto.OrderDTO;
import com.oocl.reservationsystem.entity.Orders;
import com.oocl.reservationsystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.repository=orderRepository;
    }

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
        return orders;
    }
}
