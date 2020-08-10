package com.oocl.reservationsystem.common;

import com.oocl.reservationsystem.exception.parking.ParkingLotNoFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ParkingLotNoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void parkingLotNotFound(ParkingLotNoFoundException exception){
        log.error(exception.getMessage());
    }
}