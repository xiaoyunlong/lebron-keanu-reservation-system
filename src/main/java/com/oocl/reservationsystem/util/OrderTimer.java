package com.oocl.reservationsystem.util;

import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.enums.order.OrderStatus;
import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
import com.oocl.reservationsystem.service.orderservice.OrderService;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
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
  @Autowired
  ParkingService parkingService;

  @Scheduled(fixedRate = 6000)
  public void cancelTimeoutOrder() {
    List<Order> orderList = orderService.findOrdersListByStatus(OrderStatus.NOT_USED);
    for (Order order : orderList) {
      if ((new Date().getTime() - order.getStartTime().getTime()) >= 0) {
        order.setStatus(OrderStatus.CANCELLED);
        parkingService.fetchCarOutPosition(order.getParkingPositionId());
        orderRepository.save(order);
      }
    }
  }
}
