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
  void should_return_1_parkinglot_when_find_parking_lots_by_location_given_latitude_longitude_and_2_parkinglots()
      throws Exception {
    saveParkinglot(113.579316, 22.376505, 100, 100, null, 10);
    saveParkinglot(113.561282, 22.373635, 100, 100, null, 10);

    mockMvc.perform(get("/parkinglots")
        .contentType(MediaType.APPLICATION_JSON)
        .param("latitude", String.valueOf(113.578996))
        .param("longitude", String.valueOf(22.377632)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.length()").value(1));
  }

  @Test
  void should_return_not_found_exception_when_find_parking_lots_by_location_given_latitude_longitude_and_2_parkinglots_not_in_near()
      throws Exception {
    saveParkinglot(113.579316, 22.376505, 100, 100, null, 10);
    saveParkinglot(113.561282, 22.373635, 100, 100, null, 10);

    mockMvc.perform(get("/parkinglots")
        .contentType(MediaType.APPLICATION_JSON)
        .param("latitude", String.valueOf(113.630739))
        .param("longitude", String.valueOf(22.358482)))
        .andExpect(status().isNotFound());
  }

  @Test
  void should_return_first_parkingLot_ZH03_and_second_parkingLot_ZH02_and_third_parkingLot_ZH05_when_find_parking_lots_by_price_given_5_parkingLots_and_location_latitude_longitude() throws Exception {
    saveParkinglot(113.579316, 22.376505,10,10,"ZH01",30);
    saveParkinglot(113.579803, 22.377223,20,20,"ZH02",30);
    saveParkinglot(113.578398, 22.375861,20,20,"ZH03",10);
    saveParkinglot(113.579718, 22.377313,20,20,"ZH04",100);
    saveParkinglot(113.579316, 22.376505,20,20,"ZH05",30);

    mockMvc.perform(get("/parkinglots")
            .contentType(MediaType.APPLICATION_JSON)
            .param("latitude", String.valueOf(113.579718))
            .param("longitude", String.valueOf(22.377313))
            .param("sortType", String.valueOf(2)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].name").value("ZH03"))
            .andExpect(jsonPath("$.content[1].name").value("ZH02"))
            .andExpect(jsonPath("$.content[2].name").value("ZH05"));
  }

  @Test
  void should_return_first_parkingLot_ZH02_and_second_parkingLot_ZH05_and_third_parkingLot_ZH04_when_find_parking_lots_by_distance_given_5_parkingLots_and_location_latitude_longitude() throws Exception {
    saveParkinglot(113.579316, 22.376505,10,10,"ZH01",30);
    saveParkinglot(113.579803, 22.377223,20,20,"ZH02",30);
    saveParkinglot(113.578398, 22.375861,20,20,"ZH03",10);
    saveParkinglot(113.579718, 22.377313,20,20,"ZH04",100);
    saveParkinglot(113.579316, 22.376505,20,20,"ZH05",30);

    mockMvc.perform(get("/parkinglots")
            .contentType(MediaType.APPLICATION_JSON)
            .param("latitude", String.valueOf(113.579718))
            .param("longitude", String.valueOf(22.377313))
            .param("sortType", String.valueOf(1)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].name").value("ZH04"))
            .andExpect(jsonPath("$.content[1].name").value("ZH02"))
            .andExpect(jsonPath("$.content[2].name").value("ZH05"));
  }

  private void saveParkinglot(double latitude, double longitude, int capicity, int remainingAmount, String name,
      int unitPrice) {
    ParkingLot parkingLot = new ParkingLot(latitude, longitude, capicity, remainingAmount, name, unitPrice);
    parkingLotRepository.save(parkingLot);
  }
}
