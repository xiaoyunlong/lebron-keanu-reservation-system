package com.oocl.reservationsystem.dto.logindto;

import com.oocl.reservationsystem.entity.parkingentity.Car;

import java.util.List;

public class LoginResponse {

  private int id;
  private String username;
  private String email;
  private String phoneNumber;
  private String password;
  private List<Car> cars;

  public LoginResponse(int id, String username, String email, String phoneNumber, String password,
      List<Car> cars) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.cars = cars;
  }

  public LoginResponse() {
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public List<Car> getCars() {
    return cars;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
