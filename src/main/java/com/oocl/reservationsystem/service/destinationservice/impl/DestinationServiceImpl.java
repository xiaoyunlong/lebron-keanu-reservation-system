package com.oocl.reservationsystem.service.destinationservice.impl;

import com.oocl.reservationsystem.entity.destinationentity.Destination;
import com.oocl.reservationsystem.repository.destinationrepository.DestinationRepository;
import com.oocl.reservationsystem.service.destinationservice.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

  @Autowired
  DestinationRepository destinationRepository;

  @Override
  public List<Destination> findByInputMessage(String destination) {
    return destinationRepository.findByNameWith(destination);
  }
}
