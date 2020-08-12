package com.oocl.reservationsystem.rabbitmq;

public class SendMessage {

  private int customerId;
  private int orderId;
  private String customerName;
  private String type;

  public SendMessage() {
  }

  public SendMessage(int customerId, int orderId, String customerName, String type) {
    this.customerId = customerId;
    this.orderId = orderId;
    this.customerName = customerName;
    this.type = type;
  }


  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
