package com.oocl.reservationsystem.service.rabbitservice;

import com.alibaba.fastjson.JSON;
import com.oocl.reservationsystem.dto.mqdto.MessageResponse;
import com.oocl.reservationsystem.enums.RoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

  @Autowired
  RabbitTemplate rabbitTemplate;

  public void sendMQMessage(int customerId, com.oocl.reservationsystem.dto.mqdto.MessageType messageType) {
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setCustomerId(customerId);
    messageResponse.setType(messageType.getMessage());
    rabbitTemplate.convertAndSend("directExchange", RoutingKey.NOTIFICATION_KEY.getRoutingName(),
        JSON.toJSONString(messageResponse));
  }
}
