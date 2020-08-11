package com.oocl.reservationsystem.controller.logincontroller;

import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.service.loginservice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping("/customers/{customerId}")
  @ResponseStatus(HttpStatus.OK)
  public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
    return customerService.getCustomerById(customerId);
  }
}
