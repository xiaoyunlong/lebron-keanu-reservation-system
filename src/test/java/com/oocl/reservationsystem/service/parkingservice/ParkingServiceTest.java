package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.entity.parkingentity.ParkingPosition;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingPositionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ParkingServiceTest {

    @Autowired
    ParkingService parkingService;

    @MockBean
    ParkingLotRepository parkingLotRepository;

    @MockBean
    ParkingPositionRepository parkingPositionRepository;


    @Test
    void should_return_parking_lots_when_find_parking_lots_by_location_given_latitude_longitude(){
        //given
        double latitude = 113.578996;
        double longitude = 22.377632;
        int sortType = 1;

        //给附近的两个停车场
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(113.579316, 22.376505,100,100,null,10));
        parkingLots.add(new ParkingLot(113.581109, 22.378243,100,100,null,10));
        Page<ParkingLot> page = new PageImpl<>(parkingLots);

        Mockito.when(parkingLotRepository.findByRemainingAmountGreaterThan(0,Pageable.unpaged()))
                .thenReturn(page);

        //when
        Page<ParkingLotDto> parkingLotsInResult = parkingService.findParkingLotsByLocation(latitude, longitude, sortType,Pageable.unpaged());

        //then
        Assertions.assertEquals(2, parkingLotsInResult.getContent().size());
    }

    @Test
    void should_return_true_when_find_is_Car_In_Position_given_position_id_1() {

        //given
        int positionId = 1;
        ParkingPosition parkingPosition = new ParkingPosition();
        parkingPosition.setStatus(0);
        Mockito.when(parkingPositionRepository.findById(positionId)).thenReturn(Optional.of(parkingPosition));

        //when
        boolean isCarInPosition = parkingService.isCarInPosition(positionId);

        //then
        Assertions.assertFalse(isCarInPosition);
    }

    @Test
    void should_method_used_when_park_car_in_position_given_position_id_1() {

        //given
        int positionId = 1;
        String placeName = "south parkingLot";

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(placeName);
        parkingLot.setCapicity(10);
        parkingLot.setRemainingAmount(10);

        ParkingPosition parkingPosition = new ParkingPosition();
        parkingPosition.setParkingLot(parkingLot);

        Mockito.when(parkingPositionRepository.findById(positionId))
                .thenReturn(java.util.Optional.of(parkingPosition));

        Mockito.when(parkingLotRepository.findById(parkingPosition.getParkingLot().getId()))
                .thenReturn(java.util.Optional.of(parkingLot));

        //when
        parkingService.parkCarInPosition(positionId);

        //then
        verify(parkingPositionRepository,times(1)).save(any(ParkingPosition.class));
        verify(parkingLotRepository,times(1)).save(any(ParkingLot.class));
    }

}
