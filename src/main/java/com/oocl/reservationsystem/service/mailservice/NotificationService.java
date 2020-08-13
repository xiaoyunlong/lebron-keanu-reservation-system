package com.oocl.reservationsystem.service.mailservice;

import com.oocl.reservationsystem.dto.maildto.NotificationResponseDto;
import com.oocl.reservationsystem.dto.mqdto.MessageType;

public interface NotificationService {
  NotificationResponseDto getAllNotificationByCustomerId(Integer customerId);

  void saveNotification(int customerId, MessageType messageType);
}
