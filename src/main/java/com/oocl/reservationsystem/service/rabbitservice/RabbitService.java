package com.oocl.reservationsystem.service.rabbitservice;

import com.alibaba.fastjson.JSON;
import com.oocl.reservationsystem.dto.mqdto.MessageResponse;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.enums.RoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

  @Autowired
  RabbitTemplate rabbitTemplate;

  public void sendMQMessage(OrderResponse orderResponse, com.oocl.reservationsystem.dto.mqdto.MessageType messageType) {
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setCustomerId(orderResponse.getCustomerId());
    messageResponse.setOrderId(orderResponse.getCarId());
    messageResponse.setType(messageType.getMessage());
    rabbitTemplate.convertAndSend("directExchange", RoutingKey.CREATE_ORDER_KEY.getRoutingName(),
        JSON.toJSONString(messageResponse));
  }

  public void sendCancelOrderMQMessage(int orderId, com.oocl.reservationsystem.dto.mqdto.MessageType messageType) {
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setOrderId(orderId);
    messageResponse.setType(messageType.getMessage());
    rabbitTemplate.convertAndSend("directExchange", RoutingKey.CANCEL_ORDER_KEY.getRoutingName(),
        JSON.toJSONString(messageResponse));
  }

  public void sendOrderTimeOutMQMessage(int orderId, com.oocl.reservationsystem.dto.mqdto.MessageType messageType) {
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setOrderId(orderId);
    messageResponse.setType(messageType.getMessage());
    rabbitTemplate.convertAndSend("directExchange", RoutingKey.ORDER_TIME_OUT_KEY.getRoutingName(),
        JSON.toJSONString(messageResponse));
  }

  public void sendRegisterMQMessage(int customerId, com.oocl.reservationsystem.dto.mqdto.MessageType messageType) {
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setCustomerId(customerId);
    messageResponse.setType(messageType.getMessage());
    rabbitTemplate.convertAndSend("directExchange", RoutingKey.USER_REGISTER_KEY.getRoutingName(),
        JSON.toJSONString(messageResponse));
  }
}
