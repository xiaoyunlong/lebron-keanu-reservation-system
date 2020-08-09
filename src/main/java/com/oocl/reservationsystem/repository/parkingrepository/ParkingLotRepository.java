package com.oocl.reservationsystem.repository.parkingrepository;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Integer> {

}
