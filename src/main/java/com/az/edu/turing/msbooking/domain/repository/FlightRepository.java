package com.az.edu.turing.msbooking.domain.repository;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findByDepartureDateTimeBetween(LocalDateTime start, LocalDateTime end);

}
