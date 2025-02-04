package com.az.edu.turing.msbooking.service.impl;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.FlightMapper;
import com.az.edu.turing.msbooking.mapper.UserMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;
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
    public FlightDto createFlight(CreateFlightRequest createFlightRequest) {
        FlightEntity flightEntity = flightRepository.save(flightMapper.toFlightEntity(createFlightRequest));
        return flightMapper.toFlightDto(flightEntity);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toFlightDto).collect(Collectors.toList());
    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        return flightMapper.toFlightDto(flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("No flight found with id: " + id)));
    }

    @Override
    public FlightDto updateFlight(Long id, UpdateFlightRequest updateFlightRequest) {
        if (flightRepository.existsById(id)) {
            FlightEntity flightEntity = flightMapper.toFlightEntity(updateFlightRequest);
            flightRepository.save(flightEntity);
        }
        throw new NotFoundException("No flight found with id: " + id);
    }

    @Override
    public void deleteFlight(Long flightId) {
        if (flightRepository.existsById(flightId)) {
            throw new NotFoundException("No flight found with id: " + flightId);
        }
        //soft-delete
        flightRepository.findById(flightId)
                .ifPresent(flightEntity -> {
                    flightEntity.setFlightStatus(FlightStatus.CANCELLED);
                    flightRepository.save(flightEntity);
                });

        //hard-delete
//        flightRepository.deleteById(flightId);

    }
}
