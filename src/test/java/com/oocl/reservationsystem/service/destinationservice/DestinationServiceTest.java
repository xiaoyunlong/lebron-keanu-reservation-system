package com.oocl.reservationsystem.service.destinationservice;

import com.oocl.reservationsystem.entity.destinationentity.Destination;
import com.oocl.reservationsystem.repository.destinationrepository.DestinationRepository;
import com.oocl.reservationsystem.service.destinationservice.impl.DestinationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

  @InjectMocks
  DestinationServiceImpl destinationService;

  @Mock
  DestinationRepository destinationRepository;

  @Test
  void should_return_some_fuzzy_matching_destinations_when_input_destination_partially() {
    //given
    String destination = "南方";
    List<Destination> destinations = new ArrayList<Destination>();
    Destination destination1 = new Destination();
    destination1.setId("1");
    destination1.setName("南方软件园");
    destination1.setLatitude(113.578996);
    destination1.setLongitude(22.377632);
    Destination destination2 = new Destination();
    destination1.setId("2");
    destination1.setName("南方科技园");
    destination1.setLatitude(113.603776);
    destination1.setLongitude(22.371074);
    destinations.add(destination1);
    destinations.add(destination2);

    when(destinationRepository.findByNameWith(destination))
        .thenReturn(destinations);

    //when
    List<Destination> result = destinationService.findByInputMessage(destination);

    //then
    assertEquals(2, result.size());
  }
}
