package com.oocl.reservationsystem.service.parkingservice.impl;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.enums.parking.ParkingEnum;
import com.oocl.reservationsystem.exception.parking.ParkingLotNoFoundException;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import com.oocl.reservationsystem.util.LatlongitudeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public List<ParkingLot> findParkingLotsByLocation(double latitude, double longitude) {

        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        List<ParkingLot> parkingLotsResult = parkingLots.stream()
                .filter(parkingLot -> isParkingLotInEffectiveDistance(parkingLot,latitude,longitude))
                .collect(Collectors.toList());

        if (parkingLotsResult.size() == 0) {
            throw new ParkingLotNoFoundException(ParkingEnum.PARKING_LOT_NOT_FOUND);
        }

        return parkingLotsResult;
    }

    private boolean isParkingLotInEffectiveDistance(ParkingLot parkingLot,double latitude, double longitude){
        double EFFECTIVE_DISTANCE = 5;
        return LatlongitudeUtil.getDistance
                (latitude, longitude, parkingLot.getLatitude(), parkingLot.getLongitude()) < EFFECTIVE_DISTANCE;
    }
}
