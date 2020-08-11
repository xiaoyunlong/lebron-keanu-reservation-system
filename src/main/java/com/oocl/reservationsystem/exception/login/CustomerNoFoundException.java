package com.oocl.reservationsystem.exception.login;

import com.oocl.reservationsystem.enums.login.CustomerEnum;

public class CustomerNoFoundException extends RuntimeException {

  private String message;

  public CustomerNoFoundException(CustomerEnum customerEnum) {
    this.message = customerEnum.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}
