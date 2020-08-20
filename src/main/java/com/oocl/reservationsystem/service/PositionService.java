package com.oocl.reservationsystem.service;

import com.oocl.reservationsystem.entity.ParkingPosition;

import java.util.List;

public interface PositionService {
    List<ParkingPosition> getAllPosition();

    void resetStatus(int positionId);

}
