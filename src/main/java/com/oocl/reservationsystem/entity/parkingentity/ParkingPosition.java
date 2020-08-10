package com.oocl.reservationsystem.entity.parkingentity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parking_position")
public class ParkingPosition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String parkingNumber;
  private int status;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "parkinglot_id")
  private ParkingLot parkingLot;

  public ParkingPosition() {
  }

  public ParkingPosition(String parkingNumber, int status) {
    this.parkingNumber = parkingNumber;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public String getParkingNumber() {
    return parkingNumber;
  }

  public void setParkingNumber(String parkingNumber) {
    this.parkingNumber = parkingNumber;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public ParkingLot getParkingLot() {
    return parkingLot;
  }

  public void setParkingLot(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }
}
