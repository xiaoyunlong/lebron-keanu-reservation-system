package com.oocl.reservationsystem.service.parkingservice.impl;

import com.oocl.reservationsystem.entity.parkingentity.Car;
import com.oocl.reservationsystem.exception.parking.CarNotFoundException;
import com.oocl.reservationsystem.repository.parkingrepository.CarRepository;
import com.oocl.reservationsystem.service.parkingservice.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public String getCarNumberById(Integer id) {
        Car car =  carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        return car.getCarNumber();
    }
}
