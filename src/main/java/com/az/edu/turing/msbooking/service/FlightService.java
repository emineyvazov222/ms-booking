package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto createFlight(CreateFlightRequest createFlightRequest);

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(Long flightId);

    FlightDto updateFlight(Long id, UpdateFlightRequest updateFlightRequest);

    void deleteFlight(Long flightId);
}
