package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkingService {

    Page<ParkingLotDto> findParkingLotsByLocation(double latitude, double longitude, Pageable pageable);
}
