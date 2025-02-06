package com.az.edu.turing.msbooking.domain.repository;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    boolean existsByFlightIdAndUserId(Long flightId, Long userId);

}
