package com.oocl.reservationsystem.enums.login;

public enum CustomerEnum {
  CUSTOMER_NO_FOUND("This user does not exist");

  private String message;

  CustomerEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
