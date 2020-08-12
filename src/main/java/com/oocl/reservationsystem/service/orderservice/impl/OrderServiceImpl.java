package com.oocl.reservationsystem.service.orderservice.impl;

import com.oocl.reservationsystem.dto.orderdto.OrderParkingLotRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.entity.parkingentity.Car;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.enums.order.OrderStatus;
import com.oocl.reservationsystem.exception.order.CarAlrearyParkOrReservedException;
import com.oocl.reservationsystem.exception.order.OrderCancelFailException;
import com.oocl.reservationsystem.exception.order.OrderNotFoundException;
import com.oocl.reservationsystem.exception.order.OrderParkingPositionNotSpaceException;
import com.oocl.reservationsystem.exception.order.OrderStatusErrorException;
import com.oocl.reservationsystem.exception.order.StartTimeOverHourException;
import com.oocl.reservationsystem.exception.parking.ParkingLotEventTypeErrorException;
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
  private static final String ENTER = "entering";
  private static final String EXIT = "exiting";

  public OrderServiceImpl(
      OrderRepository orderRepository, ParkingService parkingService, CarService carService) {
    this.orderRepository = orderRepository;
    this.parkingService = parkingService;
    this.carService = carService;
  }

  @Transactional
  @Override
  public OrderResponse addOrder(OrderRequest orderRequest) {

    if (orderRequest.getStartTime().getTime() - new Date().getTime() > 60 * 60 * 1000) {
      throw new StartTimeOverHourException();
    }
    if (isCarAlrearyParkOrReserved(orderRequest.getCarNumber())) {
      throw new CarAlrearyParkOrReservedException();
    }

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

  public Boolean isCarAlrearyParkOrReserved(String carNumber) {
    Car car = carService.findCarByCarNumber(carNumber);
    List<Order> parkOrder = orderRepository.findOrderByStatusAndCarId(OrderStatus.USED, car.getId());
    List<Order> reserveOrder = orderRepository.findOrderByStatusAndCarId(OrderStatus.NOT_USED, car.getId());
    return parkOrder.size() != 0 || reserveOrder.size() != 0;
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
  public Order useOrder(String carNumber) {
    Car car = carService.findCarByCarNumber(carNumber);
    List<Order> willUseOrder = orderRepository.findOrderByStatusAndCarId(OrderStatus.NOT_USED, car.getId());
    if (willUseOrder == null || willUseOrder.size() == 0) {
      throw new OrderNotFoundException();
    }
    Order order = willUseOrder.get(0);
    if (order.getStatus().equals(OrderStatus.NOT_USED)) {
      order.setEnterTime(new Date());
      order.setStatus(OrderStatus.USED);
      return orderRepository.save(order);
    } else {
      throw new OrderStatusErrorException();
    }
  }

  @Override
  public OrderResponse finishOrder(String carNumber) {
    Car car = carService.findCarByCarNumber(carNumber);
    List<Order> willFinishOrder = orderRepository.findOrderByStatusAndCarId(OrderStatus.USED, car.getId());
    if (willFinishOrder == null || willFinishOrder.size() == 0) {
      throw new OrderNotFoundException();
    }
    Order order = willFinishOrder.get(0);
    // TODO when given money,and calculate remain money,let car out the lot
    order.setEndTime(new Date());
    order.setTotalCost(
        OrdersUtil.calculateAllCost(order.getEnterTime(), order.getEndTime(), order.getPreCost()));
    if (order.getStatus().equals(OrderStatus.USED)) {
      order.setStatus(OrderStatus.FINISHED);
      parkingService.fetchCarOutPosition(order.getParkingPositionId());
      return orderToResponseMapper(orderRepository.save(order));
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
      parkingService.fetchCarOutPosition(order.getParkingPositionId());
      return orderRepository.save(order);
    } else {
      throw new OrderCancelFailException();
    }
  }

  @Override
  public List<Order> findOrdersListByStatus(String status) {
    return orderRepository.findOrdersListByStatus(status);
  }

  @Override
  public void changeStatusFromParkingLot(OrderParkingLotRequest orderParkingLotRequest) {
    if (orderParkingLotRequest.getEventType().equals(ENTER)) {
      useOrder(orderParkingLotRequest.getLicenseNumber());
    } else if (orderParkingLotRequest.getEventType().equals(EXIT)) {
      finishOrder(orderParkingLotRequest.getLicenseNumber());
    } else {
      throw new ParkingLotEventTypeErrorException();
    }
  }

  public OrderResponse orderToResponseMapper(Order order) {
    OrderResponse orderResponse = new OrderResponse();
    BeanUtils.copyProperties(order, orderResponse);

    ParkingLot parkingLot = parkingService.findParkingLotByPositionId(order.getParkingPositionId());
    orderResponse.setParkingLotName(parkingLot.getName());
    orderResponse.setLocation(parkingLot.getLocation());

    DateFormat dateFormat = new SimpleDateFormat("yyyyHHmmMMdd");
    String orderNumber =
        dateFormat.format(order.getCreateTime())
            + order.getId().toString()
            + order.getCustomerId().toString();

    orderResponse.setOrderNumber(orderNumber);
    return orderResponse;
  }
}
