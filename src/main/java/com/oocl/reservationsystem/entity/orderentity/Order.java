package com.oocl.reservationsystem.entity.orderentity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer carId;
  private Integer customerId;
  private Date createTime;
  private Date startTime;
  private Date endTime;
  private Integer totalCost;
  private Integer preCost;
  private String status;
  private Integer parkingPositionId;
  private Date enterTime;

  public Order(Integer id, Integer carId, Integer customerId, Date createTime,
      Date startTime, Date endTime, Integer totalCost, Integer preCost, String status, Integer parkingPositionId) {
    this.id = id;
    this.carId = carId;
    this.customerId = customerId;
    this.createTime = createTime;
    this.startTime = startTime;
    this.endTime = endTime;
    this.totalCost = totalCost;
    this.preCost = preCost;
    this.status = status;
    this.parkingPositionId = parkingPositionId;
  }

  public Order() {
  }

  public Date getEnterTime() {
    return enterTime;
  }

  public void setEnterTime(Date enterTime) {
    this.enterTime = enterTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
    return "Order{"
        + "id=" + id
        + ", carId=" + carId
        + ", customerId=" + customerId
        + ", createTime=" + createTime
        + ", startTime=" + startTime
        + ", endTime=" + endTime
        + ", totalCost=" + totalCost
        + ", preCost=" + preCost
        + ", status='" + status + '\''
        + ", parkingPositionId=" + parkingPositionId
        + '}';
  }
}
