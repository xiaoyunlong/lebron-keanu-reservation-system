package com.oocl.reservationsystem.controller.ordercontroller;

import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.service.orderservice.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public void addOrder(@RequestBody @Valid OrderRequest orderRequest){
        orderService.addOrder(orderRequest);
    }
    @GetMapping()
    public List<OrderResponse> getAllOrderByCustomerId(@RequestParam(value="customer_id") Integer customerId) {
        return orderService.getAllOrderByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/use/{order_id}")
    public void useOrder(@PathVariable(value = "order_id") Integer orderId){
        orderService.useOrder(orderId);
    }

}
