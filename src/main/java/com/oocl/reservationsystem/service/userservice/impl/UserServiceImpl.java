package com.oocl.reservationsystem.service.userservice.impl;

import com.oocl.reservationsystem.dto.userdto.UserDto;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.user.UserEnum;
import com.oocl.reservationsystem.exception.user.UserException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.service.userservice.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final CustomerRepository customerRepository;

  public UserServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  @Transactional
  public UserDto updateUserInfo(UserDto userDto) {
    Customer customer = customerRepository.findByEmail(userDto.getEmail());
    if (customer != null) {
      customer.setUsername(userDto.getUserName());
      customer.setPhoneNumber(userDto.getPhoneNumber());
      Customer customerInDB = customerRepository.save(customer);
      return new UserDto(customer.getEmail(), customer.getPhoneNumber(), customer.getUsername());
    }
    throw new UserException(UserEnum.UPDATE_FAILED);
  }
}
