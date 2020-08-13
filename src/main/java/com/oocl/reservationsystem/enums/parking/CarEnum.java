package com.oocl.reservationsystem.enums.parking;

public enum CarEnum {
  CAR_NOT_FOUND("car not found");

  private final String message;

  CarEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
