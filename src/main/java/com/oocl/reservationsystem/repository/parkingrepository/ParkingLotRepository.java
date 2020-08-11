package com.oocl.reservationsystem.repository.parkingrepository;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

  Page<ParkingLot> findByRemainingAmountGreaterThan(int amout, Pageable pageable);
}
