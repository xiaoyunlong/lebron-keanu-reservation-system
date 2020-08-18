package com.oocl.reservationsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Order {
    private String parkingLot;
    private String parkingPosition;
    private Date parkTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Order(String parkingLot,String parkingPosition){
        this.parkingLot=parkingLot;
        this.parkingPosition=parkingPosition;
        this.parkTime=new Date();
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getParkingPosition() {
        return parkingPosition;
    }

    public void setParkingPosition(String parkingPosition) {
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
