package com.oocl.reservationsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "parkingPosition")
public class ParkingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "parking_space")
    private String parkingSpace;
    @Column(name = "status")
    private int status;

    public ParkingPosition( String parkingSpace, int status){
        this.parkingSpace = parkingSpace;
        this.status = status;
    }

    public ParkingPosition() {
    }

    public int getId() {
        return id;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public int getStatus() {
        return status;
    }
}
