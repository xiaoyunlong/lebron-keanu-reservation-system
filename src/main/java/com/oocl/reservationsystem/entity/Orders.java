package com.oocl.reservationsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String parkingLot;
    private int parkingPosition;
    private Date parkTime;

    public Orders(String parkingLot, int parkingPosition){
        this.parkingLot=parkingLot;
        this.parkingPosition=parkingPosition;
        this.parkTime=new Date();
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
