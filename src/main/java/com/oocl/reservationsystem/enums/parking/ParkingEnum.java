package com.oocl.reservationsystem.enums.parking;

public enum ParkingEnum {

    PARKING_LOT_NOT_FOUND("parking lot not found");

    private String message;

    ParkingEnum(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
