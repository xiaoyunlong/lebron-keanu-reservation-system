package com.oocl.reservationsystem.dto.orderdto;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class OrderParkingLotRequest {

  private String address;
  @NotNull
  private String licenseNumber;
  private String parkingLotName;
  private Date time;
  @NotNull
  private String eventType;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public String getParkingLotName() {
    return parkingLotName;
  }

  public void setParkingLotName(String parkingLotName) {
    this.parkingLotName = parkingLotName;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

}
