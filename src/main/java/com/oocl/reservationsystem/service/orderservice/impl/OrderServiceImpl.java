package com.oocl.reservationsystem.service.orderservice.impl;

import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.enums.order.OrderStatus;
import com.oocl.reservationsystem.exception.order.OrderCancelFailException;
import com.oocl.reservationsystem.exception.order.OrderNotFoundException;
import com.oocl.reservationsystem.exception.order.OrderParkingPositionNotSpaceException;
import com.oocl.reservationsystem.exception.order.OrderStatusErrorException;
import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
import com.oocl.reservationsystem.service.orderservice.OrderService;
import com.oocl.reservationsystem.service.parkingservice.CarService;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import com.oocl.reservationsystem.util.OrdersUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ParkingService parkingService;
  private final CarService carService;

  public OrderServiceImpl(
      OrderRepository orderRepository, ParkingService parkingService, CarService carService) {
    this.orderRepository = orderRepository;
    this.parkingService = parkingService;
    this.carService = carService;
  }

  @Transactional
  @Override
  public OrderResponse addOrder(OrderRequest orderRequest) {
    if (parkingService.isCarInPosition(orderRequest.getParkingPositionId())) {
      throw new OrderParkingPositionNotSpaceException();
    }
    parkingService.parkCarInPosition(orderRequest.getParkingPositionId());
    Order order = new Order();
    BeanUtils.copyProperties(orderRequest, order);

    order.setCreateTime(new Date());
    order.setStatus(OrderStatus.NOT_USED);
    order.setCarId(carService.findCarByCarNumber(orderRequest.getCarNumber()).getId());
    Order saveOrder = orderRepository.save(order);
    return orderToResponseMapper(saveOrder);
  }

  @Override
  public List<OrderResponse> getAllOrderByCustomerId(Integer customerId) {
    List<Order> orderList = orderRepository.findByCustomerId(customerId);
    List<OrderResponse> orderResponseList = new ArrayList<>();
    for (Order order : orderList) {
      OrderResponse orderResponse = orderToResponseMapper(order);
      orderResponse.setLicenseNumber(carService.getCarNumberById(order.getCarId()));
      orderResponseList.add(orderResponse);
    }
    return orderResponseList;
  }

  @Override
  public OrderResponse getOrderById(Integer id) {
    OrderResponse orderResponse =
        orderToResponseMapper(
            orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    orderResponse.setLicenseNumber(carService.getCarNumberById(orderResponse.getCarId()));
    return orderResponse;
  }

  @Override
  public Order useOrder(Integer orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    if (order.getStatus().equals(OrderStatus.NOT_USED)) {
      order.setEnterTime(new Date());
      order.setStatus(OrderStatus.USED);
      return orderRepository.save(order);
    } else {
      throw new OrderStatusErrorException();
    }
  }

  @Override
  public Order cancelOrder(Integer orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    if (order.getStatus().equals(OrderStatus.NOT_USED)) {
      order.setStatus(OrderStatus.CANCELLED);
      order.setPreCost(0);
      return orderRepository.save(order);
    } else {
      throw new OrderCancelFailException();
    }
  }

  @Override
  public OrderResponse finishOrder(Integer orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    // TODO when given money,and calculate remain money,let car out the lot
    order.setEndTime(new Date());
    order.setTotalCost(
        OrdersUtil.calculateAllCost(order.getEnterTime(), order.getEndTime(), order.getPreCost()));
    if (order.getStatus().equals(OrderStatus.USED)) {
      order.setStatus(OrderStatus.FINISHED);
      return orderToResponseMapper(orderRepository.save(order));
    } else {
      throw new OrderStatusErrorException();
    }
  }

  @Override
  public List<Order> findOrdersListByStatus(String status) {
    return orderRepository.findOrdersListByStatus(status);
  }

  public OrderResponse orderToResponseMapper(Order order) {
    OrderResponse orderResponse = new OrderResponse();
    BeanUtils.copyProperties(order, orderResponse);

    ParkingLot parkingLot = parkingService.findParkingLotByPositionId(order.getParkingPositionId());
    orderResponse.setParkingLotName(parkingLot.getName());

    DateFormat dateFormat = new SimpleDateFormat("yyyyHHmmMMdd");
    String orderNumber =
        dateFormat.format(order.getCreateTime())
            + order.getId().toString()
            + order.getCustomerId().toString();

    orderResponse.setOrderNumber(orderNumber);
    return orderResponse;
  }
}
