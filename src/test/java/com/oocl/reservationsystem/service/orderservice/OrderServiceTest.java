package com.oocl.reservationsystem.service.orderservice;

import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.enums.order.OrderStatus;
import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
import com.oocl.reservationsystem.service.orderservice.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.ws.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    Order initOneOrder() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date startDate = df.parse("2020-12-11T20:18:30Z");
        OrderRequest orderRequest = new OrderRequest(1, 1, startDate, 5, 1);
        Order order = new Order(1, 1, 1, new Date(), startDate, null,
                null, 5, OrderStatus.NOT_USED, 1);
        return order;
    }

    OrderRequest initOneOrderRequest() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date startDate = df.parse("2020-12-11T20:18:30Z");
        return new OrderRequest(1, 1, startDate, 5, 1);
    }

    @Test
    void should_return_order_response_when_add_order_given_1_order_json() throws ParseException {
        //given
        Order order = initOneOrder();
        OrderRequest orderRequest = initOneOrderRequest();
        when(orderRepository.save(any())).thenReturn(order);

        //when
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        //then
        assertEquals(orderRequest.getCustomerId(), orderResponse.getCustomerId());
    }

}
