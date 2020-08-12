package com.oocl.reservationsystem.dto.parkingdto;

public class WebSocketRequest {
  private Integer parkinglotId;
  private Integer index;
  private Integer status;
  String message = "{\"parkinglotId\":1,\"index\":0,\"status\":1}";
  public Integer getParkinglotId() {
    return parkinglotId;
  }

  public void setParkinglotId(Integer parkinglotId) {
    this.parkinglotId = parkinglotId;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "WebSocketRequest{" +
        "parkinglotId=" + parkinglotId +
        ", index=" + index +
        ", status=" + status +
        '}';
  }
}
