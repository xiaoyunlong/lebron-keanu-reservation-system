package com.oocl.reservationsystem.service.loginservice.impl;

import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.login.CustomerEnum;
import com.oocl.reservationsystem.exception.login.CustomerNoFoundException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.service.loginservice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Customer getCustomerById(int customerId) {
    return customerRepository
        .findById(customerId)
        .orElseThrow(() -> new CustomerNoFoundException(CustomerEnum.CUSTOMER_NO_FOUND));
  }
}
