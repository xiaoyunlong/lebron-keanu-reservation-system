package com.oocl.reservationsystem.enums;

public enum RoutingKey {
  CREATE_ORDER_KEY("create_order"),
  CANCEL_ORDER_KEY("cancel_order"),
  ORDER_TIME_OUT_KEY("order_time_out"),
  USER_REGISTER_KEY("user_register"),
  NOTIFICATION_KEY("order_queue");

  private String routingName;

  RoutingKey(String routingName) {
    this.routingName = routingName;
  }

  public String getRoutingName() {
    return routingName;
  }
}
