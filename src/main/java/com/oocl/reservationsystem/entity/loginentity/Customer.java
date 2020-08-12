package com.oocl.reservationsystem.entity.loginentity;

import com.oocl.reservationsystem.entity.mailentity.Notification;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.entity.parkingentity.Car;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String username;
  private String email;
  private String password;

  @Column(name = "phone_number")
  private String phoneNumber;

  @OneToMany(mappedBy = "customer")
  private List<Car> cars;

  @OneToMany(mappedBy = "customer")
  private List<Notification> notifications;

  @OneToMany(mappedBy = "customer")
  private List<Order> orders;

  public Customer() {
  }

  public Customer(String username, String email, String phoneNumber) {
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public List<Car> getCars() {
    return cars;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
}
