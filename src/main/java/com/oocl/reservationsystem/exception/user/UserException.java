package com.oocl.reservationsystem.exception.user;

import com.oocl.reservationsystem.enums.user.UserEnum;

public class UserException extends RuntimeException {

  private final String message;

  public UserException(UserEnum userEnum) {
    this.message = userEnum.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}
