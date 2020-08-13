package com.oocl.reservationsystem.service.orderservice;

import com.oocl.reservationsystem.dto.orderdto.OrderPageResponse;
import com.oocl.reservationsystem.dto.orderdto.OrderParkingLotRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;

public interface OrderService {

  OrderResponse addOrder(OrderRequest orderRequest);

  OrderPageResponse getAllOrderByCustomerId(Integer customerId, Integer pageSize, Integer pageNumber);

  OrderResponse getOrderById(Integer id);

  Order useOrder(String carNumber);

  Order cancelOrder(Integer orderId);

  OrderResponse finishOrder(String carNumber);

  List<Order> findOrdersListByStatus(String status);

  void changeStatusFromParkingLot(OrderParkingLotRequest orderParkingLotRequest);

  public List<OrderResponse> getAllOrderByCustomerIdNotPage(Integer customerId);

}
