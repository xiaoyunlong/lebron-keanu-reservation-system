package com.oocl.reservationsystem.entity.parkingentity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "parking_position")
public class ParkingPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String parking_number;
    private int status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkingLot;

    public ParkingPosition() {
    }

    public ParkingPosition(String parking_number, int status) {
        this.parking_number = parking_number;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getParking_number() {
        return parking_number;
    }

    public void setParking_number(String parking_number) {
        this.parking_number = parking_number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

}
