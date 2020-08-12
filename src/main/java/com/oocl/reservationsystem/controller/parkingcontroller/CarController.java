package com.oocl.reservationsystem.controller.parkingcontroller;

import com.oocl.reservationsystem.dto.parkingdto.CarRequest;
import com.oocl.reservationsystem.entity.parkingentity.Car;
import com.oocl.reservationsystem.service.parkingservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

  private final CarService carService;

  @Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping("/cars")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Car addCarByCarRequest(@RequestBody CarRequest carRequest) {
    return carService.saveCar(carRequest);
  }

  @DeleteMapping("/cars/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCarById(@PathVariable("id") Integer id) {
    carService.deleteCar(id);
  }
}
