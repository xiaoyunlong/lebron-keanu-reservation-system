package com.oocl.reservationsystem.exception.parking;

import com.oocl.reservationsystem.enums.parking.CarEnum;

public class CarNotFoundException extends RuntimeException {

  private String message;

  public CarNotFoundException() {

  }

  public CarNotFoundException(CarEnum carEnum) {
    this.message = carEnum.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}
