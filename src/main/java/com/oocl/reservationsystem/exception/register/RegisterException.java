package com.oocl.reservationsystem.exception.register;

import com.oocl.reservationsystem.enums.register.RegisterEnum;

public class RegisterException extends RuntimeException {

  private final String message;

  public RegisterException(RegisterEnum registerEnum) {
    this.message = registerEnum.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}
