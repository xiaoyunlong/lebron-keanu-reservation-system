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
    @Column(name = "park_time")
    private Date parkTime;

    public Orders(String parkingLot, int parkingPosition, Date date){
        this.parkingLot=parkingLot;
        this.parkingPosition=parkingPosition;
        this.parkTime=date;
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
        return parkTime;
    }

    public void setParkTime(Date parkTime) {
        this.parkTime = parkTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
