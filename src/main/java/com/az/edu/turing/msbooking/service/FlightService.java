package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.model.dto.response.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto getFlightById(Long flightId);

    List<FlightDto> getAllFlights();
}
