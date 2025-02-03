package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {

    FlightDto getFlightById(Long flightId);

    FlightDto createFlight(CreateFlightRequest request);

    Page<FlightDto> getAllFlights(Pageable pageable);
}
