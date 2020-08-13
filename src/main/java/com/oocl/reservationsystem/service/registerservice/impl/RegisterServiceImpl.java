package com.oocl.reservationsystem.service.registerservice.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.dto.logindto.RegisterRequest;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.register.RegisterEnum;
import com.oocl.reservationsystem.exception.register.RegisterException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.service.registerservice.RegisterService;
import com.oocl.reservationsystem.util.LoginUtil;
import org.springframework.beans.BeanUtils;
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
    Customer customerToRes = new Customer();
    BeanUtils.copyProperties(registerRequest, customerToRes);
    customerToRes.setPassword(LoginUtil.stringMD5(registerRequest.getPassword()));
    customerToRes.setCars(new ArrayList<>());
    Customer customerSavedInDB = customerRepository.save(customerToRes);
    LoginResponse loginResponse = new LoginResponse();
    BeanUtils.copyProperties(customerSavedInDB, loginResponse);
    return loginResponse;
  }
}
