package com.oocl.reservationsystem.service.loginservice.impl;

import com.oocl.reservationsystem.dto.logindto.LoginRequest;
import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.login.CustomerEnum;
import com.oocl.reservationsystem.exception.login.CustomerNoFoundException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.repository.parkingrepository.CarRepository;
import com.oocl.reservationsystem.service.loginservice.LoginService;
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
        .findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    if (customers.size() == 1) {
      LoginResponse loginResponse = new LoginResponse();
      Customer currentUser = customers.get(0);
      loginResponse.setId(currentUser.getId());
      loginResponse.setUsername(currentUser.getUsername());
      loginResponse.setEmail(currentUser.getEmail());
      loginResponse.setPhoneNumber(currentUser.getPhoneNumber());
      loginResponse.setPassword(loginRequest.getPassword());
      loginResponse.setCars(currentUser.getCars());
      return loginResponse;
    }
    throw new CustomerNoFoundException(CustomerEnum.ACCOUNT_OR_PASSWORD_IS_INCORRECT);
  }
}
