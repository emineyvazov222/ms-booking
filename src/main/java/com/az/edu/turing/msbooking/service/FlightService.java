package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.exception.UnauthorizedException;
import com.az.edu.turing.msbooking.mapper.FlightMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.FlightFilter;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;
import com.az.edu.turing.msbooking.specification.FlightSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.az.edu.turing.msbooking.model.enums.Role.ADMIN;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;


    public FlightDto createFlight(CreateFlightRequest createFlightRequest, String role) {
        checkIfAdmin(role);
        FlightEntity flightEntity = flightRepository.save(flightMapper.toFlightEntity(createFlightRequest));
        return flightMapper.toFlightDto(flightEntity);
    }

    public List<FlightDto> getAllFlights() {
        return flightRepository.findByDepartureDateTimeBetween(LocalDateTime.now(),
                        LocalDateTime.now().plusHours(24)).stream()
                .map(flightMapper::toFlightDto).collect(Collectors.toList());
    }

    public FlightDto getFlightById(Long flightId) {
        return flightMapper.toFlightDto(flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("No flight found with id: " + id)));
    }

    public List<FlightEntity> filterFlights(FlightFilter filter) {
        Specification<FlightEntity> spec = FlightSpecification.filterFlights(filter);
        return flightRepository.findAll((Sort) spec);
    }

    public FlightDto updateFlight(Long id, UpdateFlightRequest updateFlightRequest, String role) {
        checkIfAdmin(role);
        FlightEntity updateFlightEntity = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No flight found with id: " + id));

        return flightMapper.toFlightDto(flightRepository.save(flightMapper
                .toFlightEntity(updateFlightRequest, updateFlightEntity)));
    }

    public void deleteFlight(Long flightId, String role) {
        checkIfAdmin(role);
        if (!flightRepository.existsById(flightId)) {
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

    private void checkIfAdmin(String role) {
        if (!ADMIN.name().equalsIgnoreCase(role)) {
            log.error("Unauthorized operation: User is not admin.");
            throw new UnauthorizedException("This operation can only be performed by administrators.");
        }
    }
}
