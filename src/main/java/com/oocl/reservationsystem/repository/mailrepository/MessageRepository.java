package com.oocl.reservationsystem.repository.mailrepository;

import com.oocl.reservationsystem.entity.mailentity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Notification, Integer> {

}
