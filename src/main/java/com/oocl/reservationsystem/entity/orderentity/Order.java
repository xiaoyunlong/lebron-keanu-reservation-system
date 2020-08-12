package com.oocl.reservationsystem.entity.orderentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.entity.parkingentity.Car;
import com.oocl.reservationsystem.entity.parkingentity.ParkingPosition;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "start_time")
  private Date startTime;

  @Column(name = "end_time")
  private Date endTime;

  @Column(name = "total_cost")
  private Integer totalCost;

  @Column(name = "pre_cost")
  private Integer preCost;

  @Column(name = "status")
  private String status;

  @Column(name = "enter_time")
  private Date enterTime;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "parking_position_id")
  private ParkingPosition parkingPosition;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;

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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public ParkingPosition getParkingPosition() {
    return parkingPosition;
  }

  public void setParkingPosition(ParkingPosition parkingPosition) {
    this.parkingPosition = parkingPosition;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

}
