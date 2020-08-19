package com.oocl.reservationsystem.service;

import com.oocl.reservationsystem.dto.OrderDTO;
import com.oocl.reservationsystem.entity.Orders;

public interface OrderService {
    Orders addOrder(OrderDTO orderDTO);
    Orders toOrderEntity(OrderDTO orderDTO,Orders orders);
}
