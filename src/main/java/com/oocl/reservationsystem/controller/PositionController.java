package com.oocl.reservationsystem.controller;

import com.oocl.reservationsystem.entity.ParkingPosition;
import com.oocl.reservationsystem.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions")
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingPosition> getAllPosition(){
        return positionService.getAllPosition();
    }

}
