package com.az.edu.turing.msbooking.service.impl;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.FlightMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    @Override
    public FlightDto getFlightById(Long flightId) {
        return flightMapper.toFlightDto(flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("No flight found with id: " + flightId)));
    }

    @Override
    public FlightDto createFlight(CreateFlightRequest request) {
        FlightEntity saved = flightRepository.save(flightMapper.toFlightEntity(request));
        return flightMapper.toFlightDto(saved);
    }

    @Override
    public Page<FlightDto> getAllFlights(Pageable pageable) {
        return flightRepository.findAll(pageable)
                .map(flightMapper::toFlightDto);
    }
}
