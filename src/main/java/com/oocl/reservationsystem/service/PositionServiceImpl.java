package com.oocl.reservationsystem.service;

import com.oocl.reservationsystem.entity.ParkingPosition;
import com.oocl.reservationsystem.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }


    @Override
    public List<ParkingPosition> getAllPosition() {
        return positionRepository.findAll();
    }

    @Override
    public void resetStatus(int positionId) {
        ParkingPosition parkingPosition = positionRepository.findById(positionId).get();
        int status = parkingPosition.getStatus();
        if(status == 0){
            parkingPosition.setStatus(1);
        }else{
            parkingPosition.setStatus(0);
        }
        positionRepository.save(parkingPosition);
    }
}
