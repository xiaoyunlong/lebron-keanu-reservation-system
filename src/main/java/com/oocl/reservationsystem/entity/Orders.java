package com.oocl.reservationsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "parking_lot")
    private String parkingLot;
    @Column(name = "parking_position")
    private int parkingPosition;
    @Column(name = "reserve_time")
    private Date reserveTime;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;

    public Orders(String parkingLot, int parkingPosition, Date startTime,Date endTime){
        this.parkingLot=parkingLot;
        this.parkingPosition=parkingPosition;
        this.reserveTime=new Date();
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public Orders(){}

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

    public Date getParkTime() {
        return reserveTime;
    }

    public void setParkTime(Date parkTime) {
        this.reserveTime = parkTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
