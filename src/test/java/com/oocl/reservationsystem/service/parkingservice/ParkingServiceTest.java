package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

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

        //给附近的两个停车场
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(113.579316, 22.376505,100,100,null,10));
        parkingLots.add(new ParkingLot(113.561282, 22.373635,100,100,null,10));
        Page<ParkingLot> page = new PageImpl<ParkingLot>(parkingLots);

        Mockito.when(parkingLotRepository.findByRemaining_amountGreaterThan(0, Pageable.unpaged()))
                .thenReturn(page);

        //when
        Page<ParkingLot> parkingLotsInResult = parkingService.findParkingLotsByLocation(latitude, longitude, Pageable.unpaged());

        //then
        Assertions.assertEquals(2, parkingLotsInResult.getContent().size());
    }

}
