package com.oocl.reservationsystem.repository.parkingrepository;

import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Integer> {
    Page<ParkingLot> findByRemaining_amountGreaterThan(int amount, Pageable pageable);
}
