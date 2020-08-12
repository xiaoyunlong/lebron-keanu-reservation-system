package com.oocl.reservationsystem.service.parkingservice.impl;

import com.oocl.reservationsystem.dto.parkingdto.ParkingLotDto;
import com.oocl.reservationsystem.dto.parkingdto.WebSocketRequest;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.entity.parkingentity.ParkingPosition;
import com.oocl.reservationsystem.enums.parking.ParkingEnum;
import com.oocl.reservationsystem.enums.parking.ParkingPositionStatusEnum;
import com.oocl.reservationsystem.exception.parking.ParkingLotNoFoundException;
import com.oocl.reservationsystem.exception.parking.ParkingLotNoSpaceException;
import com.oocl.reservationsystem.exception.parking.PositionHaveParkedException;
import com.oocl.reservationsystem.exception.parking.PositionNoFoundException;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingLotRepository;
import com.oocl.reservationsystem.repository.parkingrepository.ParkingPositionRepository;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import com.oocl.reservationsystem.util.LatlongitudeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

  private final ParkingLotRepository parkingLotRepository;
  private final ParkingPositionRepository parkingPositionRepository;

  private static final double EFFECTIVE_DISTANCE = 2000;
  private static final int SORT_IN_DISTANCE = 1;
  private static final int SORT_IN_PRICE = 2;

  @Autowired
  public ParkingServiceImpl(
      ParkingLotRepository parkingLotRepository,
      ParkingPositionRepository parkingPositionRepository) {
    this.parkingLotRepository = parkingLotRepository;
    this.parkingPositionRepository = parkingPositionRepository;
  }

  @Override
  public Page<ParkingLotDto> findParkingLotsByLocation(
      double latitude, double longitude, int sortType, Pageable pageable) {

    Page<ParkingLot> parkingLotsPage =
        parkingLotRepository.findByRemainingAmountGreaterThan(0, pageable);
    List<ParkingLotDto> parkingLotDtos = new ArrayList<>();

    for (ParkingLot parkingLot : parkingLotsPage.getContent()) {
      ParkingLotDto parkingLotDto = new ParkingLotDto();
      BeanUtils.copyProperties(parkingLot, parkingLotDto);
      parkingLotDto.setDistance(calculateDistance(parkingLot, latitude, longitude));
      parkingLotDtos.add(parkingLotDto);
    }

    if (sortType == SORT_IN_PRICE) {
      sortedByPrice(parkingLotDtos);
    } else if (sortType == SORT_IN_DISTANCE) {
      sortedByDistance(parkingLotDtos);
    }

    List<ParkingLotDto> content =
        parkingLotDtos.stream()
            .filter(parkingLotDto -> parkingLotDto.getDistance() <= EFFECTIVE_DISTANCE)
            .collect(Collectors.toList());

    if (content.size() == 0) {
      throw new ParkingLotNoFoundException(ParkingEnum.PARKING_LOT_NOT_FOUND);
    }

    return new PageImpl<>(content, pageable, parkingLotsPage.getTotalElements());
  }

  @Override
  public boolean isCarInPosition(int positionId) {
    Optional<ParkingPosition> parkingPosition = parkingPositionRepository.findById(positionId);
    if (parkingPosition.get() == null) {
      throw new PositionNoFoundException(ParkingEnum.PARKING_POSITION_NOT_FOUND);
    }
    return parkingPosition.get().getStatus()
        == ParkingPositionStatusEnum.HAVE_BEEN_PARKED.getState();
  }

  @Transactional
  @Override
  public void parkCarInPosition(int positionId) {
    ParkingPosition parkingPosition =
        parkingPositionRepository
            .findById(positionId)
            .orElseThrow(
                () -> new PositionNoFoundException(ParkingEnum.PARKING_POSITION_NOT_FOUND));

    if (parkingPosition.getStatus() == ParkingPositionStatusEnum.HAVE_BEEN_PARKED.getState()) {
      throw new PositionHaveParkedException(ParkingEnum.PARKING_POSITION_HAVE_BEEN_PARKED);
    }
    parkingPosition.setStatus(ParkingPositionStatusEnum.HAVE_BEEN_PARKED.getState());
    ParkingLot parkingLotInDB = findParkingLotByPositionId(positionId);

    if (parkingLotInDB.getRemainingAmount() <= 0) {
      throw new ParkingLotNoSpaceException(ParkingEnum.PARKING_LOT_HAVE_NO_SPACE);
    }

    parkingLotInDB.setRemainingAmount(parkingLotInDB.getRemainingAmount() - 1);
    parkingPositionRepository.save(parkingPosition);
    parkingLotRepository.save(parkingLotInDB);
  }

  @Override
  public ParkingLot findParkingLotByPositionId(int positionId) {
    ParkingPosition parkingPosition =
        parkingPositionRepository
            .findById(positionId)
            .orElseThrow(() -> new PositionNoFoundException(ParkingEnum.PARKING_LOT_NOT_FOUND));

    return parkingLotRepository
        .findById(parkingPosition.getParkingLot().getId())
        .orElseThrow(() -> new ParkingLotNoFoundException(ParkingEnum.PARKING_LOT_NOT_FOUND));
  }

  @Override
  public ParkingLot updateParkingLotByParkingLotIdAndStatus(WebSocketRequest webSocketRequest) {
    Optional<ParkingLot> parkingLot = parkingLotRepository.findById(webSocketRequest.getParkinglotId());
    if (parkingLot.isPresent()) {
      parkingLot.get().getParkingPositions().get(webSocketRequest.getIndex()).setStatus(webSocketRequest.getStatus());
    }
    return parkingLot.get();
  }

  private double calculateDistance(ParkingLot parkingLot, double latitude, double longitude) {
    return LatlongitudeUtil.getDistance(
        latitude, longitude, parkingLot.getLatitude(), parkingLot.getLongitude());
  }

  private void sortedByPrice(List<ParkingLotDto> parkingLotDtos) {
    parkingLotDtos.sort(
        (o1, o2) -> {
          if (o1.getUnitPrice() == o2.getUnitPrice()) {
            if (o1.getDistance() == o2.getDistance()) {
              return o2.getRemainingAmount() - o1.getRemainingAmount();
            }
            return (int) (o1.getDistance() - o2.getDistance());
          }
          return o1.getUnitPrice() - o2.getUnitPrice();
        });
  }

  private void sortedByDistance(List<ParkingLotDto> parkingLotDtos) {
    parkingLotDtos.sort(
        (o1, o2) -> {
          if (o1.getDistance() == o2.getDistance()) {
            if (o1.getRemainingAmount() == o2.getRemainingAmount()) {
              return o1.getUnitPrice() - o2.getUnitPrice();
            }
            return o2.getRemainingAmount() - o1.getRemainingAmount();
          }
          return (int) (o1.getDistance() - o2.getDistance());
        });
  }
}
