package com.oocl.reservationsystem.repository.orderrepository;

import com.oocl.reservationsystem.entity.orderentity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> findByCustomerId(Integer customerId);

  @Query(value = "select * from orders where status=?1", nativeQuery = true)
  List<Order> findOrdersListByStatus(String status);
}
