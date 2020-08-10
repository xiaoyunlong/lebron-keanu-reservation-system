package com.oocl.reservationsystem.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersUtil {

    public static Integer calculateAllCost(Date start, Date end, Integer unitPrice) {
        double hours = Math.ceil((double) (end.getTime() - start.getTime()) / 3600000.0);
        return (int) (hours) * unitPrice;
    }

    public static OrderResponse OrderToResponseMapper(Order order){
        OrderResponse  orderResponse = new OrderResponse();
        BeanUtils.copyProperties(order,orderResponse);

        DateFormat dateFormat = new SimpleDateFormat("yyyyHHmmMMdd");
        String orderNumber = dateFormat.format(order.getCreateTime())
                +order.getId().toString()
                +order.getCustomerId().toString();

        orderResponse.setOrderNumber(orderNumber);
        return orderResponse;
    }
}
