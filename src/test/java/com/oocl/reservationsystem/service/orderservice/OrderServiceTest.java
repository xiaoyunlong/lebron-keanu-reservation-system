//package com.oocl.reservationsystem.service.orderservice;
//
//import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
//import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
//import com.oocl.reservationsystem.entity.orderentity.Order;
//import com.oocl.reservationsystem.enums.order.OrderStatus;
//import com.oocl.reservationsystem.repository.orderrepository.OrderRepository;
//import com.oocl.reservationsystem.service.orderservice.impl.OrderServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.xml.ws.Response;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderServiceTest {
//    @InjectMocks
//    OrderServiceImpl orderService;
//
//    @Mock
//    OrderRepository orderRepository;
//
//    Order initOneOrder() throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        Date startDate = df.parse("2020-12-11T20:18:30Z");
//        return new Order(1, 1, 1, new Date(), startDate, null,
//                null, 5, OrderStatus.NOT_USED, 1);
//    }
//
//    OrderRequest initOneOrderRequest() throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        Date startDate = df.parse("2020-12-11T20:18:30Z");
//        return new OrderRequest(1, 1, startDate, 5, 1);
//    }
//
//    @Test
//    void should_return_order_response_when_add_order_given_1_order_json() throws ParseException {
//        //given
//        Order order = initOneOrder();
//        OrderRequest orderRequest = initOneOrderRequest();
//        when(orderRepository.save(any())).thenReturn(order);
//
//        //when
//        OrderResponse orderResponse = orderService.addOrder(orderRequest);
//
//        //then
//        assertEquals(orderRequest.getCustomerId(), orderResponse.getCustomerId());
//    }
//
//    @Test
//    void should_return_someone_customer_all_order_when_get_all_order_by_customer_id_given_customer_id() throws ParseException {
//        //given
//        Order order = initOneOrder();
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
//        int customerId = 1;
//        //when
//        when(orderRepository.findByCustomerId(any())).thenReturn(orderList);
//        List<OrderResponse> responseList = orderService.getAllOrderByCustomerId(customerId);
//        //then
//        assertEquals(customerId, responseList.get(0).getCustomerId());
//    }
//
//    @Test
//    void should_return_one_order_when_get_order_by_id_given_order_id() throws ParseException {
//        //given
//        Order order = initOneOrder();
//        int orderId = 1;
//        //when
//        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(order));
//        OrderResponse orderResponse = orderService.getOrderById(orderId);
//        //then
//        assert order != null;
//        assertEquals(order.getCarId(), orderResponse.getCarId());
//    }
//
//    @Test
//    void should_change_order_status_used_when_change_status_to_used_given_order_id() throws ParseException {
//        //given
//        Order order = initOneOrder();
//        Order usedOrder = initOneOrder();
//        usedOrder.setStatus(OrderStatus.USED);
//        int orderId = 1;
//        //when
//        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(order));
//        when(orderRepository.save(any())).thenReturn(usedOrder);
//
//        Order saveOrder = orderService.useOrder(orderId);
//        //then
//        assert order != null;
//        assertEquals(OrderStatus.USED, saveOrder.getStatus());
//    }
//
//    @Test
//    void should_return_status_canceled_when_change_status_to_cancel_given_order_id() throws ParseException {
//        //given
//        Order order = initOneOrder();
//        Order usedOrder = initOneOrder();
//        usedOrder.setStatus(OrderStatus.CANCELLED);
//        int orderId = 1;
//        //when
//        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(order));
//        when(orderRepository.save(any())).thenReturn(usedOrder);
//
//        Order saveOrder = orderService.cancelOrder(orderId);
//        //then
//        assert order != null;
//        assertEquals(OrderStatus.CANCELLED, saveOrder.getStatus());
//    }
//
//    @Test
//    void should_return_order_response_when_change_status_to_finish_given_order_id() throws ParseException {
//        //given
//        Order order = initOneOrder();
//        order.setStatus(OrderStatus.USED);
//        order.setEnterTime(new Date());
//        Order usedOrder = initOneOrder();
//        usedOrder.setStatus(OrderStatus.FINISHED);
//        int orderId = 1;
//        //when
//        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
//        when(orderRepository.save(any())).thenReturn(usedOrder);
//
//        OrderResponse orderResponse = orderService.finishOrder(orderId);
//        //then
//        assertEquals(OrderStatus.FINISHED, orderResponse.getStatus());
//    }
//}
