package com.oocl.reservationsystem.controller.mailcontroller;

import com.oocl.reservationsystem.dto.maildto.NotificationResponseDto;
import com.oocl.reservationsystem.service.mailservice.NotificationService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

  @Autowired
  private NotificationService notificationService;

  @GetMapping("/notifications")
  public NotificationResponseDto getNotificationsByCustomerId(@RequestParam @NotNull Integer customerId) {
    return notificationService.getAllNotificationByCustomerId(customerId);
  }

}
