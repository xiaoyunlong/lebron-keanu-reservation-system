package com.oocl.reservationsystem.service.parkingservice;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import com.oocl.reservationsystem.dto.parkingdto.WebSocketRequest;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.entity.parkingentity.ParkingPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ParkingService {

  Page<ParkingLotDto> findParkingLotsByLocation(
      double latitude, double longitude, int sortType, Pageable pageable);

  boolean isCarInPosition(int positionId);

  void parkCarInPosition(int positionId);

  ParkingLot findParkingLotByPositionId(int positionId);

  List<ParkingPosition> findParkingPositionsByParkinglotId(int id);

  ParkingLot updateParkingLotByParkingLotIdAndStatus(WebSocketRequest webSocketRequest);
}
