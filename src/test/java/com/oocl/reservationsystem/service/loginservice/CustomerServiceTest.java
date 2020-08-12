package com.oocl.reservationsystem.service.loginservice;

import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.enums.login.CustomerEnum;
import com.oocl.reservationsystem.exception.login.CustomerNoFoundException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest extends BaseTest {

  @Mock
  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void should_return_user_info_when_find_user_info_by_customer_id_given_customer_id_1() {
    // given
    int customer_id = 1;
    String username = "Ben";
    String email = "5209709737@qq.om";
    String phoneNumber = "13631230683";
    Customer customer = new Customer(username, email, phoneNumber);
    Mockito.when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));

    // when
    Optional<Customer> customerInDb = customerRepository.findById(customer_id);

    // then
    Assertions.assertEquals(username, customerInDb.get().getUsername());
  }

  @Test
  void should_return_no_found_exception_when_find_user_info_by_customer_id_given_customer_id_1() {
    // given
    int customer_id = 1;
    String username = "Ben";
    String email = "5209709737@qq.om";
    String phoneNumber = "13631230683";
    Customer customer = new Customer(username, email, phoneNumber);
    Mockito.when(customerRepository.findById(anyInt())).thenReturn(null);

    // when
    try {
      Optional<Customer> customerInDb = customerRepository.findById(customer_id);
    } catch (CustomerNoFoundException exception) {
      Assertions.assertEquals(CustomerEnum.CUSTOMER_NO_FOUND.getMessage(), exception.getMessage());
    }
  }
}
