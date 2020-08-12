package com.oocl.reservationsystem.dto.orderdto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderResponse {

  private Integer id;
  private String orderNumber;
  private Integer carId;
  private Integer customerId;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
  private Date createTime;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
  private Date startTime;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
  private Date endTime;
  private Integer totalCost;
  private Integer preCost;
  private String status;
  private Integer parkingPositionId;
  private String parkingLotName;
  private String licenseNumber;
  private String location;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getParkingLotName() {
    return parkingLotName;
  }

  public void setParkingLotName(String parkingLotName) {
    this.parkingLotName = parkingLotName;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Integer getCarId() {
    return carId;
  }

  public void setCarId(Integer carId) {
    this.carId = carId;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Integer getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(Integer totalCost) {
    this.totalCost = totalCost;
  }

  public Integer getPreCost() {
    return preCost;
  }

  public void setPreCost(Integer preCost) {
    this.preCost = preCost;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getParkingPositionId() {
    return parkingPositionId;
  }

  public void setParkingPositionId(Integer parkingPositionId) {
    this.parkingPositionId = parkingPositionId;
  }

  @Override
  public String toString() {
    return "OrderResponse{"
        + "orderNumber='"
        + orderNumber
        + '\''
        + ", carId="
        + carId
        + ", customerId="
        + customerId
        + ", createTime="
        + createTime
        + ", startTime="
        + startTime
        + ", endTime="
        + endTime
        + ", totalCost="
        + totalCost
        + ", preCost="
        + preCost
        + ", status='"
        + status
        + '\''
        + ", parkingPositionId="
        + parkingPositionId
        + '}';
  }
}
