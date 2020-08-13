package com.oocl.reservationsystem.service.parkingservice.impl;

import com.oocl.reservationsystem.dto.parkingdto.CarRequest;
import com.oocl.reservationsystem.entity.parkingentity.Car;
import com.oocl.reservationsystem.enums.parking.CarEnum;
import com.oocl.reservationsystem.exception.parking.CarNotFoundException;
import com.oocl.reservationsystem.repository.loginrepository.CustomerRepository;
import com.oocl.reservationsystem.repository.parkingrepository.CarRepository;
import com.oocl.reservationsystem.service.parkingservice.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final CustomerRepository customerRepository;

  public CarServiceImpl(CarRepository carRepository,
      CustomerRepository customerRepository) {
    this.carRepository = carRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public String getCarNumberById(Integer id) {
    Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(CarEnum.CAR_NOT_FOUND));
    return car.getCarNumber();
  }

  @Override
  public Car findCarByCarNumber(String carNumber) {
    if (null == carRepository.findTheCarByCarNumber(carNumber)) {
      throw new CarNotFoundException(CarEnum.CAR_NOT_FOUND);
    }
    return carRepository.findTheCarByCarNumber(carNumber);
  }

  @Override
  public Car saveCar(CarRequest carRequest) {
    Car car = new Car();
    car.setCarNumber(carRequest.getCarNumber());
    car.setCustomer(customerRepository.findById(carRequest.getCustomerId()).get());
    return carRepository.save(car);
  }

  @Override
  public void deleteCar(Integer id) {
    carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(CarEnum.CAR_NOT_FOUND));
    carRepository.deleteById(id);
  }
}
