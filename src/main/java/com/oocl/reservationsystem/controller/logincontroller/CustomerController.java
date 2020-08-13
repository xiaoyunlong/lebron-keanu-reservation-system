package com.oocl.reservationsystem.controller.logincontroller;

import com.oocl.reservationsystem.dto.orderdto.OrderPageResponse;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.entity.mailentity.Notification;
import com.oocl.reservationsystem.service.loginservice.CustomerService;
import com.oocl.reservationsystem.service.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

  private final CustomerService customerService;
  private final OrderService orderService;

  @Autowired
  public CustomerController(CustomerService customerService,
      OrderService orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  @RequestMapping("/customers/{customerId}")
  @ResponseStatus(HttpStatus.OK)
  public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
    return customerService.getCustomerById(customerId);
  }

  @GetMapping(value = "/customers/{id}/orders", params = {"pageNumber", "pageSize"})
  public OrderPageResponse getAllOrderByCustomerId(@PathVariable Integer id, @RequestParam Integer pageSize,
      @RequestParam Integer pageNumber) {
    return orderService.getAllOrderByCustomerId(id, pageSize, pageNumber);
  }

  @GetMapping(value = "/customers/{id}/orders")
  public List<OrderResponse> getAllOrderByCustomerId1(@PathVariable Integer id) {
    return orderService.getAllOrderByCustomerIdNotPage(id);
  }

  @GetMapping("/customers/{id}/notifications")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<Notification> getAllNotificationsByCustomerId(@PathVariable("id") Integer id) {
    return customerService.getCustomerById(id).getNotifications();
  }
}
