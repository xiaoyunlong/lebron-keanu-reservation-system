package com.oocl.reservationsystem.util;

import com.oocl.reservationsystem.dto.orderdto.OrderResponse;
import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.service.parkingservice.impl.ParkingServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;

@Component
public class OrdersUtil {

  public static Integer calculateAllCost(Date start, Date end, Integer unitPrice) {
    double hours = Math.ceil((double) (end.getTime() - start.getTime()) / 3600000.0);
    return (int) (hours) * unitPrice;
  }


}
