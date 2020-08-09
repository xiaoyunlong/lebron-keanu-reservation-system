package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    void should_return_parking_lots_when_find_parkinglots_by_location_given_latitude_longitude(){
        //given
        double latitude = 113.578996;
        double longitude = 22.377632;

        //给附近的两个停车场
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(113.579316, 22.376505,100,100,null,10));
        parkingLots.add(new ParkingLot(113.561282, 22.373635,100,100,null,10));

        Mockito.when(parkingLotRepository.findAll())
                .thenReturn(parkingLots);

        //when
        List<ParkingLot> parkingLotsInResult = parkingService.findParkingLotsByLocation(latitude, longitude);

        //then
        Assertions.assertEquals(2, parkingLotsInResult.size());
    }

}
