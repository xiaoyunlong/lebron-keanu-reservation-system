package com.oocl.reservationsystem.controller.parkingcontroller;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/parkinglots")
    public Page<ParkingLotDto> getParkingLots
            (Pageable pageable, @RequestParam double latitude, @RequestParam double longitude, @RequestParam(defaultValue = "1") int sortType) {
        return parkingService.findParkingLotsByLocation(latitude, longitude, sortType, pageable);
    }

}
