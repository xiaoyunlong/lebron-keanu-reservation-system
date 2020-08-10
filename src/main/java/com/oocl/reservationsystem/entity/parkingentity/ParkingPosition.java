package com.oocl.reservationsystem.entity.parkingentity;

import javax.persistence.*;

@Entity
@Table(name = "parking_position")
public class ParkingPosition {

    public ParkingPosition(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
