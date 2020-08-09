package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParkingService {

    Page<ParkingLot> findParkingLotsByLocation(double latitude, double longitude, Pageable pageable);
}
