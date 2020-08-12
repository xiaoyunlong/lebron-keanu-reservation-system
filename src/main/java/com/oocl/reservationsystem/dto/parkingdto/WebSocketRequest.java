package com.oocl.reservationsystem.dto.parkingdto;

import com.google.gson.annotations.Expose;

public class WebSocketRequest {
  @Expose
  private Integer parkinglotId;
  @Expose
  private Integer parkingPositionId;
  @Expose
  private Integer status;

  public Integer getParkinglotId() {
    return parkinglotId;
  }

  public void setParkinglotId(Integer parkinglotId) {
    this.parkinglotId = parkinglotId;
  }

  public int getParkingPositionId() {
    return parkingPositionId;
  }

  public void setParkingPositionId(int parkingPositionId) {
    this.parkingPositionId = parkingPositionId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "WebSocketRequest{"
        + "parkinglotId=" + parkinglotId
        + ", index=" + parkingPositionId
        + ", status=" + status
        + '}';
  }
}
