package com.oocl.reservationsystem.repository.orderrepository;

import com.oocl.reservationsystem.entity.orderentity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

}
