package com.oocl.reservationsystem.controller.ordercontroller;

import com.oocl.reservationsystem.dto.orderdto.OrderParkingLotRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.service.orderservice.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping()
  public void addOrder(@RequestBody @Valid OrderRequest orderRequest) {
    orderService.addOrder(orderRequest);
  }

  @GetMapping("/{id}")
  public OrderResponse getOrderById(@PathVariable Integer id) {
    return orderService.getOrderById(id);
  }

  @PutMapping("/cancel/{order_id}")
  public void cancelOrder(@PathVariable(value = "order_id") Integer orderId) {
    orderService.cancelOrder(orderId);
  }

  @PutMapping()
  public void changeStatusFromParkingLot(@RequestBody @Valid OrderParkingLotRequest orderParkingLotRequest) {
    orderService.changeStatusFromParkingLot(orderParkingLotRequest);
  }
}
