package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;

import java.util.List;

public interface ParkingService {

    List<ParkingLot> findParkingLotsByLocation(double latitude, double longitude);
}
