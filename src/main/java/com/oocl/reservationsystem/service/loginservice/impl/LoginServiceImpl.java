package com.oocl.reservationsystem.service.loginservice.impl;

import com.oocl.reservationsystem.dto.logindto.LoginRequest;
import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.login.CustomerEnum;
import com.oocl.reservationsystem.exception.login.CustomerNoFoundException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.repository.parkingrepository.CarRepository;
import com.oocl.reservationsystem.service.loginservice.LoginService;
import com.oocl.reservationsystem.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CarRepository carRepository;

  @Override
  public LoginResponse getCustomerLoginRequest(LoginRequest loginRequest) {
    List<Customer> customers = customerRepository
        .findByEmailAndPassword(loginRequest.getEmail(), LoginUtil.stringMD5(loginRequest.getPassword()));
    if (customers.size() == 1) {
      Customer currentUser = customers.get(0);
      return new LoginResponse(currentUser.getId(), currentUser.getUsername(),
          currentUser.getEmail(), currentUser.getPhoneNumber(), LoginUtil.stringMD5(loginRequest.getPassword()),
          currentUser.getCars());
    }
    throw new CustomerNoFoundException(CustomerEnum.ACCOUNT_OR_PASSWORD_IS_INCORRECT);
  }
}
