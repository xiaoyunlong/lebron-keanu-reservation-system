package com.oocl.reservationsystem.enums.parking;

public enum ParkingEnum {

    PARKING_LOT_NOT_FOUND("parking lot not found"),
    PARKING_POSITION_NOT_FOUND("parking position not found"),
    PARKING_POSITION_HAVE_BEEN_PARKED("parking position have been parked"),
    PARKING_LOT_HAVE_NO_SPACE("parking lot have no space");

    private String message;

    ParkingEnum(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
