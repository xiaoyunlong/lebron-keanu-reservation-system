package com.oocl.reservationsystem.repository.parkingrepository;

import com.oocl.reservationsystem.entity.parkingentity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {}
