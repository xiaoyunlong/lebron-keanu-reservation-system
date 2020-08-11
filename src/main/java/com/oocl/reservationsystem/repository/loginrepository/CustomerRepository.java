package com.oocl.reservationsystem.repository.loginrepository;

import com.oocl.reservationsystem.entity.loginentity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
