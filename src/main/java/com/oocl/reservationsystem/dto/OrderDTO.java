package com.oocl.reservationsystem.dto;

import javax.persistence.Column;
import java.util.Date;

public class OrderDTO {
    private String parkingLot;
    private int parkingPosition;
    private Date startTime;
    private Date endTime;

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

    public OrderDTO(String parkingLot, int parkingPosition, Date startTime, Date endTime) {
        this.parkingLot = parkingLot;
        this.parkingPosition = parkingPosition;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public OrderDTO(){}
}
