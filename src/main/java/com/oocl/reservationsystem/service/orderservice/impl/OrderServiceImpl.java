package com.oocl.reservationsystem.service.orderservice.impl;

import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.enums.order.OrderStatus;
import com.oocl.reservationsystem.exception.order.OrderCancelFailException;
import com.oocl.reservationsystem.exception.order.OrderNotFoundException;
import com.oocl.reservationsystem.exception.order.OrderStatusErrorException;
import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
import com.oocl.reservationsystem.service.orderservice.OrderService;
import com.oocl.reservationsystem.util.OrdersUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        //TODO must first judge whether there is a car in the parking_postition.
        //TODO use porkingLot and prkingPosition to change status.
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        order.setCreateTime(new Date());
        order.setStatus(OrderStatus.NOT_USED);
        System.out.println(order.toString());
        return OrdersUtil.OrderToResponseMapper(orderRepository.save(order));
    }

    @Override
    public List<OrderResponse> getAllOrderByCustomerId(Integer customerId) {
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(Order order:orderList){
            orderResponseList.add(OrdersUtil.OrderToResponseMapper(order));
        }
        return orderResponseList;
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        return OrdersUtil.OrderToResponseMapper(orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public Order useOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if (order.getStatus().equals(OrderStatus.NOT_USED)) {
            order.setStatus(OrderStatus.USED);
            return orderRepository.save(order);
        } else {
            throw new OrderStatusErrorException();
        }
    }
}
