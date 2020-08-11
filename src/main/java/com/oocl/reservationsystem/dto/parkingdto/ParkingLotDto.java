package com.oocl.reservationsystem.dto.parkingdto;

public class ParkingLotDto {

  private String name;
  private int capicity;
  private int remainingAmount;
  private int unitPrice;
  private double distance;
  private String location;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public int getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
