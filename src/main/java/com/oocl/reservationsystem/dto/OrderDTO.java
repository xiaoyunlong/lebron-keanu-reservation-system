package com.oocl.reservationsystem.dto;

public class OrderDTO {
    private String parkingLot;
    private int parkingPosition;

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public int getParkingPosition() {
        return parkingPosition;
    }

    public void setParkingPosition(int parkingPosition) {
        this.parkingPosition = parkingPosition;
    }

    public OrderDTO(String parkingLot, int parkingPosition) {
        this.parkingLot = parkingLot;
        this.parkingPosition = parkingPosition;
    }

    public OrderDTO(){}
}
