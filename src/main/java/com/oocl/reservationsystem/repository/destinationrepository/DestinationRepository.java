package com.oocl.reservationsystem.repository.destinationrepository;

import com.oocl.reservationsystem.entity.destinationentity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {

  @Query(value = "select * from destination where name like CONCAT('%',:name,'%')", nativeQuery = true)
  List<Destination> findByNameWith(@Param("name") String name);

}
