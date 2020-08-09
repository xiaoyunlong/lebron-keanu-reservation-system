package com.oocl.reservationsystem.service.parkingservice.impl;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.enums.parking.ParkingEnum;
import com.oocl.reservationsystem.exception.parking.ParkingLotNoFoundException;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import com.oocl.reservationsystem.util.LatlongitudeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ParkingLot> findParkingLotsByLocation(double latitude, double longitude, Pageable pageable) {

        Page<ParkingLot> parkingLotsPage = parkingLotRepository.findByRemaining_amountGreaterThan(0,pageable);
        List<ParkingLot> content = parkingLotsPage.getContent().stream()
                .filter(parkingLot -> isParkingLotInEffectiveDistance(parkingLot,latitude,longitude))
                .collect(Collectors.toList());

        if (content.size() == 0) {
            throw new ParkingLotNoFoundException(ParkingEnum.PARKING_LOT_NOT_FOUND);
        }

        return new PageImpl<>(content, pageable, parkingLotsPage.getTotalElements());
    }

    private boolean isParkingLotInEffectiveDistance(ParkingLot parkingLot,double latitude, double longitude){
        double EFFECTIVE_DISTANCE = 5;
        return LatlongitudeUtil.getDistance
                (latitude, longitude, parkingLot.getLatitude(), parkingLot.getLongitude()) < EFFECTIVE_DISTANCE;
    }
}
