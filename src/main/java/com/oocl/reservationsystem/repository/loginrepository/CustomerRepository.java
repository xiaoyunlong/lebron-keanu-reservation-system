package com.oocl.reservationsystem.repository.loginrepository;

import com.oocl.reservationsystem.entity.loginentity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  @Query(value = "SELECT * FROM `customer` c WHERE c.email= ?1 AND c.`password` = ?2", nativeQuery = true)
  List<Customer> findByEmailAndPassword(String email,String password);
}
