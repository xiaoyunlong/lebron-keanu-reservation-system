package com.oocl.reservationsystem.dto.maildto;

import com.oocl.reservationsystem.entity.mailentity.Notification;

import java.util.List;

public class NotificationResponseDto {

  private String username;
  private List<Notification> notifications;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }
}
