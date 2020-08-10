package com.oocl.reservationsystem.common;

import com.oocl.reservationsystem.exception.order.OrderCancelFailException;
import com.oocl.reservationsystem.exception.order.OrderNotFoundException;
import com.oocl.reservationsystem.exception.order.OrderStatusErrorException;
import com.oocl.reservationsystem.exception.parking.ParkingLotNoFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return exception.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String orderNotFoundException(){
        return "order not found.";
    }

    @ExceptionHandler(OrderCancelFailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String orderCancelFailException(){
        return "order cancel failed.";
    }

    @ExceptionHandler(OrderStatusErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String orderStatusErrorException(){
        return "Order status error.";
    }

    @ExceptionHandler(ParkingLotNoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void parkingLotNotFound(ParkingLotNoFoundException exception){
        log.error(exception.getMessage());
    }
}