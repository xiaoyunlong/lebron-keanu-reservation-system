package com.oocl.reservationsystem.util;

import java.util.Date;

public class OrdersUtil {

    public static Integer calculateAllCost(Date start, Date end, Integer unitPrice) {
        double hours = Math.ceil((double) (end.getTime() - start.getTime()) / 3600000.0);
        return (int) (hours) * unitPrice;
    }

}
