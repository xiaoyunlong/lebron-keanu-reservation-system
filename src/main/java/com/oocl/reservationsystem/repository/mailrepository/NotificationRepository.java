package com.oocl.reservationsystem.repository.mailrepository;

import com.oocl.reservationsystem.entity.mailentity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {



}
