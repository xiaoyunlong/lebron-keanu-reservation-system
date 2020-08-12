package com.oocl.reservationsystem.dto.mqdto;

public enum MessageType {
  REVERSE_MESSAGE("reverse_order"),
  ORDER_CANCEL_MESSAGE("cancel_order"),
  REGISTER_MESSAGE("register"),
  ORDER_TIMEOUT_MESSAGE("order_time_out");

  private String message;

  MessageType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
