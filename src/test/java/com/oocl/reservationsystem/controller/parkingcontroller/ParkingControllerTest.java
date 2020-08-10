package com.oocl.reservationsystem.controller.parkingcontroller;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        parkingLotRepository.deleteAll();
    }

    @Test
    void should_return_1_parkinglot_when_find_parking_lots_by_location_given_latitude_longitude_and_2_parkinglots() throws Exception {
        saveParkinglot(113.579316, 22.376505,100,100,null,10);
        saveParkinglot(113.561282, 22.373635,100,100,null,10);

        mockMvc.perform(get("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .param("latitude", String.valueOf(113.578996))
                .param("longitude", String.valueOf(22.377632)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    void should_return_not_found_exception_when_find_parking_lots_by_location_given_latitude_longitude_and_2_parkinglots_not_in_near() throws Exception {
        saveParkinglot(113.579316, 22.376505,100,100,null,10);
        saveParkinglot(113.561282, 22.373635,100,100,null,10);

        mockMvc.perform(get("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .param("latitude", String.valueOf(113.630739))
                .param("longitude", String.valueOf(22.358482)))
                .andExpect(status().isNotFound());
    }

    private void saveParkinglot(double latitude, double longitude ,int capicity, int remainingAmount, String name, int unitPrice) {
        ParkingLot parkingLot = new ParkingLot(latitude, longitude, capicity, remainingAmount, name, unitPrice);
        parkingLotRepository.save(parkingLot);
    }
}
