package com.oocl.reservationsystem.repository.parkingrepository;

import com.oocl.reservationsystem.entity.parkingentity.ParkingPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPositionRepository extends JpaRepository<ParkingPosition, Integer> {}
