package com.oocl.reservationsystem.enums.login;

public enum CustomerEnum {
  CUSTOMER_NO_FOUND("This user does not exist"),
  ACCOUNT_OR_PASSWORD_IS_INCORRECT("The user name or password is incorrect");

  private String message;

  CustomerEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
