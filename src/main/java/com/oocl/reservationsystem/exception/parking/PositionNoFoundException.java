package com.oocl.reservationsystem.exception.parking;

import com.oocl.reservationsystem.enums.parking.ParkingEnum;

public class PositionNoFoundException extends RuntimeException {

  private String message;

  public PositionNoFoundException(ParkingEnum parkingEnum) {
    this.message = parkingEnum.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }

}
