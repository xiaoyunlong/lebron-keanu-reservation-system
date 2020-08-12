package com.oocl.reservationsystem.repository.parkingrepository;

import com.oocl.reservationsystem.entity.orderentity.Order;
import com.oocl.reservationsystem.entity.parkingentity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
  @Query(value = "select * from car where car_number=?1", nativeQuery = true)
  Car findTheCarByCarNumber(String carNumber);
}
