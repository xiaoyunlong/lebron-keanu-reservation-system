package com.oocl.reservationsystem.repository;

import com.oocl.reservationsystem.entity.ParkingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<ParkingPosition,Integer> {

}
