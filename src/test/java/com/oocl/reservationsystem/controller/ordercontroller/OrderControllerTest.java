//package com.oocl.reservationsystem.controller.ordercontroller;
//
//import com.oocl.reservationsystem.entity.orderentity.Order;
//import com.oocl.reservationsystem.enums.order.OrderStatus;
//import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class OrderControllerTest {
//
//  @Autowired
//  private OrderRepository orderRepository;
//  @Autowired
//  private MockMvc mockMvc;
//
//  @BeforeEach
//  void tearUp() {
//    orderRepository.deleteAll();
//  }
//
//  @AfterEach
//  void tearDown() {
//    orderRepository.deleteAll();
//  }
//
//  Order initOneOrder() throws ParseException {
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//    Date startDate = df.parse("2020-12-11T20:18:30Z");
//    return new Order(1, 1, 1, new Date(), startDate, null,
//        null, 5, OrderStatus.NOT_USED, 1);
//  }
//
//  @Test
//  void should_return_order_when_get_order_by_customer_id_given_customer_id() throws Exception {
//    Order order = initOneOrder();
//    Order saveOrder = orderRepository.save(order);
//
//    mockMvc.perform(get("/orders?customer_id=1")
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("[0].carId").value(saveOrder.getCarId()));
//  }
//
//  @Test
//  void should_return_one_order_when_get_order_by_id_given_order_id() throws Exception {
//    Order order = initOneOrder();
//    Order saveOrder = orderRepository.save(order);
//    Integer saveOrderId = saveOrder.getId();
//    mockMvc.perform(get("/orders/" + saveOrderId.toString())
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("id").value(saveOrderId));
//  }
//
//  @Test
//  void should_return_code_200_when_add_one_order_given_order_json() throws Exception {
//    String newOrder = "{\n"
//        + "\t\"customerId\": \"1\",\n"
//        + "\t\"carId\" : \"1\",\n"
//        + "\t\"startTime\": \"2020-12-11T20:12:30Z\",\n"
//        + "\t\"parkingPositionId\":\"3\",\n"
//        + "\t\"preCost\":\"5\"\n"
//        + "}";
//    mockMvc.perform(post("/orders")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(newOrder))
//        .andExpect(status().isOk());
//  }
//
//  @Test
//  void should_return_200_when_cancel_order_given_order_id() throws Exception {
//
//    Order order = initOneOrder();
//    Order saveOrder = orderRepository.save(order);
//    Integer saveOrderId = saveOrder.getId();
//    mockMvc.perform(put("/orders/cancel/" + saveOrderId.toString())
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk());
//  }
//
//}
