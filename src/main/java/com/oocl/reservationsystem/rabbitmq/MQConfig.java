package com.oocl.reservationsystem.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

  @Bean
  Queue createOrderQueue() {
    return new Queue("order_queue", true);
  }

  @Bean
  DirectExchange directExchange() {
    return new DirectExchange("directExchange", true, false);
  }

}
