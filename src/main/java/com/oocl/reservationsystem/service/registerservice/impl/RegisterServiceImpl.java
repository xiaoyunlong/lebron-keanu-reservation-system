package com.oocl.reservationsystem.service.registerservice.impl;

import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.dto.logindto.RegisterRequest;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.register.RegisterEnum;
import com.oocl.reservationsystem.exception.register.RegisterException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.service.registerservice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegisterServiceImpl implements RegisterService {

  @Autowired
  private CustomerRepository customerRepository;


  @Override
  public LoginResponse getCustomerRegisterRequest(RegisterRequest registerRequest) {
    Customer customer = customerRepository.findByEmail(registerRequest.getEmail());
    if (customer != null) {
      throw new RegisterException(RegisterEnum.ALREADY_REGISTERED);
    }
    Customer customer1 = new Customer(registerRequest.getUsername(), registerRequest.getEmail(),
        registerRequest.getPhoneNumber());
    customer1.setPassword(registerRequest.getPassword());
    customer1.setCars(new ArrayList<>());

    Customer customerSavedInDB = customerRepository.save(customer1);
    return new LoginResponse(customerSavedInDB.getId(), customerSavedInDB.getUsername(),
        customerSavedInDB.getEmail(), customerSavedInDB.getPhoneNumber(), customerSavedInDB.getPassword(),
        customerSavedInDB.getCars());
  }
}
