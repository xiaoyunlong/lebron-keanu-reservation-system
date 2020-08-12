package com.oocl.reservationsystem.service.destinationservice;

import com.oocl.reservationsystem.entity.destinationentity.Destination;

import java.util.List;

public interface DestinationService {

  List<Destination> findByInputMessage(String destination);
}
