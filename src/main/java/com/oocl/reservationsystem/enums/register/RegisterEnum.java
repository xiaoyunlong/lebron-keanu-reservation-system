package com.oocl.reservationsystem.enums.register;

public enum RegisterEnum {
  ALREADY_REGISTERED("The user is already registered");

  private String message;

  RegisterEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
