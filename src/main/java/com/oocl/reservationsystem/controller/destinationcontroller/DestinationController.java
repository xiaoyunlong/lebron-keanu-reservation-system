package com.oocl.reservationsystem.controller.destinationcontroller;

import com.oocl.reservationsystem.entity.destinationentity.Destination;
import com.oocl.reservationsystem.service.destinationservice.DestinationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DestinationController {

  private final DestinationService destinationService;

  public DestinationController(DestinationService destinationService) {
    this.destinationService = destinationService;
  }

  @GetMapping("/destinations")
  public List<Destination> findDestinationByInputLike(@RequestParam String inputMessage) {
    return destinationService.findByInputMessage(inputMessage);
  }
}
