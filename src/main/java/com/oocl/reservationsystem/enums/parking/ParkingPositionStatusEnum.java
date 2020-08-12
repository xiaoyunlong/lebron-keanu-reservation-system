package com.oocl.reservationsystem.enums.parking;

public enum ParkingPositionStatusEnum {
  HAVE_BEEN_PARKED(1),
  HAVE_NOT_PARKED(0);

  private final int state;

  ParkingPositionStatusEnum(int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }
}
