package com.oocl.reservationsystem.service.orderservice;

import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;

import java.util.List;

public interface OrderService {

    OrderResponse addOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrderByCustomerId(Integer customerId);

    OrderResponse getOrderById(Integer id);

    Order useOrder(Integer orderId);

    Order cancelOrder(Integer orderId);

    OrderResponse finishOrder(Integer orderId);

    List<Order>findOrdersListByStatus(String status);
}
