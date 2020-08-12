package com.oocl.reservationsystem.dto.parkingdto;

public class CarRequest {
  private String carNumber;
  private Integer customerId;

  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }
}
