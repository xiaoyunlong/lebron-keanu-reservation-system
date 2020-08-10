package com.oocl.reservationsystem.exception.parking;

import com.oocl.reservationsystem.enums.parking.ParkingEnum;

public class PositionHaveParkedException extends RuntimeException{

    private String message;

    public PositionHaveParkedException(ParkingEnum parkingEnum){
        this.message = parkingEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
