package com.oocl.reservationsystem;

import com.oocl.reservationsystem.entity.ParkingPosition;
import com.oocl.reservationsystem.repository.PositionRepository;
import com.oocl.reservationsystem.service.PositionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest extends BaseTest{

    @Autowired
    PositionService positionService;

    @Autowired
    PositionRepository positionRepository;

    @Test
    void should_return_2_position_when_search_all_position_given_2_position_in_db(){

        //given
        ParkingPosition parkingPosition1 = new ParkingPosition("A401",0);
        ParkingPosition parkingPosition2 = new ParkingPosition("A401",0);
        positionRepository.save(parkingPosition1);
        positionRepository.save(parkingPosition2);
        //when
        List<ParkingPosition> positions =  positionService.getAllPosition();
        //then
        Assertions.assertEquals(2,positions.size());
    }
}
