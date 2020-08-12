package com.oocl.reservationsystem.entity.parkingentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parkinglot")
public class ParkingLot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Expose
  private int id;
  @Expose
  private double latitude;
  @Expose
  private double longitude;
  @Expose
  private int capicity;

  @Column(name = "remaining_amount")
  @Expose
  private int remainingAmount;
  @Expose
  private String name;

  @Column(name = "unit_price")
  @Expose
  private int unitPrice;
  @Expose
  private String location;

  @JsonIgnore
  @OneToMany(fetch= FetchType.EAGER,mappedBy = "parkingLot")
  @Expose
  private List<ParkingPosition> parkingPositions;

  public ParkingLot() {}

  public ParkingLot(
      double latitude,
      double longitude,
      int capicity,
      int remainingAmount,
      String name,
      int unitPrice) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.capicity = capicity;
    this.remainingAmount = remainingAmount;
    this.name = name;
    this.unitPrice = unitPrice;
  }

  public int getId() {
    return id;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public int getCapicity() {
    return capicity;
  }

  public void setCapicity(int capicity) {
    this.capicity = capicity;
  }

  public int getRemainingAmount() {
    return remainingAmount;
  }

  public void setRemainingAmount(int remainingAmount) {
    this.remainingAmount = remainingAmount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }

  public List<ParkingPosition> getParkingPositions() {
    return parkingPositions;
  }

  public void setParkingPositions(List<ParkingPosition> parkingPositions) {
    this.parkingPositions = parkingPositions;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
