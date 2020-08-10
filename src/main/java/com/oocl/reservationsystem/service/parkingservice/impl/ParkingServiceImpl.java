package com.oocl.reservationsystem.service.parkingservice.impl;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.enums.parking.ParkingEnum;
import com.oocl.reservationsystem.exception.parking.ParkingLotNoFoundException;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import com.oocl.reservationsystem.util.LatlongitudeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingLotRepository parkingLotRepository;
    private final double EFFECTIVE_DISTANCE = 1;
    private final int SORT_IN_DISTANCE = 1;
    private final int SORT_IN_PRICE = 2;


    @Autowired
    public ParkingServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Page<ParkingLotDto> findParkingLotsByLocation(double latitude, double longitude, int sortType, Pageable pageable) {

        Page<ParkingLot> parkingLotsPage = parkingLotRepository.findByRemainingAmountGreaterThan(0,pageable);
        List<ParkingLotDto> parkingLotDtos = new ArrayList<>();

        for (ParkingLot parkingLot : parkingLotsPage.getContent()) {
            ParkingLotDto parkingLotDto = new ParkingLotDto();
            BeanUtils.copyProperties(parkingLot, parkingLotDto);
            parkingLotDto.setDistance(calculateDistance(parkingLot, latitude, longitude));
            parkingLotDtos.add(parkingLotDto);
        }

        if (sortType == SORT_IN_PRICE) {
            sortedByPrice(parkingLotDtos);
        } else if (sortType == SORT_IN_DISTANCE) {
            sortedByDistance(parkingLotDtos);
        }

        List<ParkingLotDto> content = parkingLotDtos.stream()
                .filter(parkingLotDto -> parkingLotDto.getDistance() <= EFFECTIVE_DISTANCE)
                .collect(Collectors.toList());

        if (content.size() == 0) {
            throw new ParkingLotNoFoundException(ParkingEnum.PARKING_LOT_NOT_FOUND);
        }

        return new PageImpl<>(content, pageable, parkingLotsPage.getTotalElements());
    }

    private double calculateDistance(ParkingLot parkingLot,double latitude, double longitude) {
        return LatlongitudeUtil.getDistance
                (latitude, longitude, parkingLot.getLatitude(), parkingLot.getLongitude());
    }

    private void sortedByPrice(List<ParkingLotDto> parkingLotDtos){
        parkingLotDtos.sort((o1, o2) -> {
            if (o1.getUnitPrice() == o2.getUnitPrice()) {
                if (o1.getDistance() == o2.getDistance()) {
                    return o2.getRemainingAmount() - o1.getRemainingAmount();
                }
                return (int) (o1.getDistance() - o2.getDistance());
            }
            return o1.getUnitPrice() - o2.getUnitPrice();
        });
    }

    private void sortedByDistance(List<ParkingLotDto> parkingLotDtos){
        parkingLotDtos.sort((o1, o2) -> {
            if (o1.getDistance() == o2.getDistance()) {
                if (o1.getRemainingAmount() == o2.getRemainingAmount()) {
                    return o1.getUnitPrice() - o2.getUnitPrice();
                }
                return o2.getRemainingAmount() - o1.getRemainingAmount();
            }
            return (int) (o1.getDistance() - o2.getDistance());
        });
    }
}
