package com.oocl.reservationsystem.service.loginservice;

import com.oocl.reservationsystem.entity.loginentity.Customer;

public interface CustomerService {

  Customer getCustomerById(int customerId);
}
