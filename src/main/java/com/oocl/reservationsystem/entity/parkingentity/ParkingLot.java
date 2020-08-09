package com.oocl.reservationsystem.entity.parkingentity;

import javax.persistence.*;

@Entity
@Table(name = "parkinglot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double latitude;
    private double longitude;
    private int capicity;
    private int remaining_amount;
    private String name;
    private int unit_price;

    public ParkingLot(double latitude, double longitude, int capicity, int remaining_amount, String name, int unit_price) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.capicity = capicity;
        this.remaining_amount = remaining_amount;
        this.name = name;
        this.unit_price = unit_price;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
