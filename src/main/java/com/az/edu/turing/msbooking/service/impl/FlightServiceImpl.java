package com.az.edu.turing.msbooking.service.impl;

import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.FlightMapper;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        return flightMapper.toFlightDto(flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("No flight found with id: " + id)));
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toFlightDto).collect(Collectors.toList());
    }
}
