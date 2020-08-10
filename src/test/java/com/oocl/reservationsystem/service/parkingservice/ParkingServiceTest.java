package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
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

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ParkingServiceTest {

    @Autowired
    ParkingService parkingService;

    @MockBean
    ParkingLotRepository parkingLotRepository;


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

}
