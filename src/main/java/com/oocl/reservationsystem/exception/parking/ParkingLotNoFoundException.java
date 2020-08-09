package com.oocl.reservationsystem.exception.parking;

import com.oocl.reservationsystem.enums.parking.ParkingEnum;

public class ParkingLotNoFoundException extends RuntimeException{

    private String message;

    public ParkingLotNoFoundException(ParkingEnum parkingEnum){
        this.message = parkingEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
