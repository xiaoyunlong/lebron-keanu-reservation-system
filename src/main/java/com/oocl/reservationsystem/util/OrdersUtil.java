package com.oocl.reservationsystem.util;

import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.service.parkingservice.impl.ParkingServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrdersUtil {
    @Autowired  // 注入
    private ParkingServiceImpl parkingService;

     //声明对象
    public static OrdersUtil ordersUtil;

    @PostConstruct // 初始化
    public void init() {
        ordersUtil = this;
        ordersUtil.parkingService = this.parkingService;
    }

    public static Integer calculateAllCost(Date start, Date end, Integer unitPrice) {
        double hours = Math.ceil((double) (end.getTime() - start.getTime()) / 3600000.0);
        return (int) (hours) * unitPrice;
    }

    public  static OrderResponse OrderToResponseMapper(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(order, orderResponse);

        ParkingLot parkingLot = ordersUtil.parkingService.findParkingLotByPositionId(order.getParkingPositionId());
        orderResponse.setParkingLotName(parkingLot.getName());

        DateFormat dateFormat = new SimpleDateFormat("yyyyHHmmMMdd");
        String orderNumber = dateFormat.format(order.getCreateTime())
                + order.getId().toString()
                + order.getCustomerId().toString();

        orderResponse.setOrderNumber(orderNumber);
        return orderResponse;
    }


}
