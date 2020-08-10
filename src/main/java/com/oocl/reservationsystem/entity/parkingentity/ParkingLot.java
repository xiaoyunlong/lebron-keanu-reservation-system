package com.oocl.reservationsystem.entity.parkingentity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parkinglot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double latitude;
    private double longitude;
    private int capicity;
    @Column(name = "remaining_amount")
    private int remainingAmount;
    private String name;
    @Column(name = "unit_price")
    private int unitPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingPosition> parkingPositions;

    public ParkingLot() {
    }

    public ParkingLot(double latitude, double longitude, int capicity, int remainingAmount, String name, int unitPrice) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.capicity = capicity;
        this.remainingAmount = remainingAmount;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCapicity() {
        return capicity;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
