package com.oocl.reservationsystem.service.mailservice.impl;

import com.oocl.reservationsystem.dto.maildto.NotificationResponseDto;
import com.oocl.reservationsystem.dto.mqdto.MessageType;
import com.oocl.reservationsystem.entity.loginentity.Customer;
import com.oocl.reservationsystem.entity.mailentity.Notification;
import com.oocl.reservationsystem.repository.mailrepository.NotificationRepository;
import com.oocl.reservationsystem.service.loginservice.CustomerService;
import com.oocl.reservationsystem.service.mailservice.NotificationService;
import org.h2.mvstore.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private NotificationRepository notificationRepository;

  @Override
  public NotificationResponseDto getAllNotificationByCustomerId(Integer customerId) {

    Customer customer = customerService.getCustomerById(customerId);

    List<Notification> before = customer.getNotifications();
    //Collections.reverse(before);
    customer.setNotifications(before);

    NotificationResponseDto notificationResponseDto = new NotificationResponseDto();
    BeanUtils.copyProperties(customer, notificationResponseDto);

    return notificationResponseDto;
  }

  @Override
  public void saveNotification(int customerId, MessageType messageType) {
    Notification notification = new Notification();
    Customer customer = customerService.getCustomerById(customerId);

    notification.setCustomer(customer);
    notification.setNotificationType(messageType.getMessage());
    notification.setCreateTime(getCurrentTime());

    notificationRepository.save(notification);
  }

  private String getCurrentTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return formatter.format(new Date());
  }
}
