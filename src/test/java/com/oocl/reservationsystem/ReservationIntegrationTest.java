package com.oocl.reservationsystem;

import com.oocl.reservationsystem.entity.Orders;
import com.oocl.reservationsystem.repository.OrderRepository;
import com.oocl.reservationsystem.service.OrderService;
import com.oocl.reservationsystem.service.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @BeforeEach
    void prepareData(){}

    @AfterEach
    void tearDown(){

    }

    @Test
    void should_return_the_specific_order_when_post_an_order_given_an_order() throws Exception {
        String orderJson="{\n" +
                "\t\"parkingLot\":\"e-parking\",\n" +
                "\t\"parkingPosition\":13,\n" +
                "\t\"startTime\": \"2020-08-19T11:26:57.082+00:00\",\n" +
                "\t\"endTime\": \"2020-08-19T11:26:57.082+00:00\"\n" +
                "}";
        mockMvc.perform(post("/order").
                contentType(MediaType.APPLICATION_JSON).
                content(orderJson)).andExpect(status().isCreated());
        List<Orders> orders=orderRepository.findByParkingPosition(10);
        Assertions.assertEquals(1,orders.size());
    }
}
