package com.oocl.reservationsystem.rabbitmq;

public class MessageType {

  public static final String CREATE_ORDER = "reverse_order";

  public static final String CANCEL_ORDER = "cancel_order";

  public static final String ORDER_TIME_OUT = "order_time_out";

  public static final String USER_REGISTER = "user_register";
}
