package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.dto.parkingdto.CarRequest;
import com.oocl.reservationsystem.entity.parkingentity.Car;

public interface CarService {

  String getCarNumberById(Integer id);

  Car findCarByCarNumber(String carNumber);

  Car saveCar(CarRequest carRequest);

  void deleteCar(Integer id);
}
