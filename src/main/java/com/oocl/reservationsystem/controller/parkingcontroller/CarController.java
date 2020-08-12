package com.oocl.reservationsystem.controller.parkingcontroller;

import com.oocl.reservationsystem.dto.parkingdto.CarRequest;
import com.oocl.reservationsystem.service.parkingservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

  private final CarService carService;

  @Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping("/cars")
  public void getParkingLotById(@RequestBody CarRequest carRequest) {
    carService.saveCar(carRequest);
  }

  @DeleteMapping("/cars/{id}")
  public void getParkingLotById(@PathVariable("id") Integer id) {
    carService.deleteCar(id);
  }
}
