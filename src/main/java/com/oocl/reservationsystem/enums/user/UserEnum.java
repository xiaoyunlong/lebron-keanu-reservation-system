package com.oocl.reservationsystem.enums.user;

public enum UserEnum {

  UPDATE_FAILED("The update to the user failed");

  private String message;

  UserEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
