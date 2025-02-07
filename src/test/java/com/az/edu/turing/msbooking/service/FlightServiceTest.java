package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.FlightMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;

import com.az.edu.turing.msbooking.model.dto.response.FlightDto;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.az.edu.turing.msbooking.common.FlightTestConstant.*;
import static com.az.edu.turing.msbooking.common.UserTestConstant.ROLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightMapper flightMapper;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    void createFlight_ShouldReturnSuccess() {
        when(flightMapper.toFlightEntity(any(CreateFlightRequest.class))).thenReturn(FLIGHT_ENTITY);
        when(flightRepository.save(any(FlightEntity.class))).thenReturn(FLIGHT_ENTITY);
        when(flightMapper.toFlightDto(any(FlightEntity.class))).thenReturn(FLIGHT_DTO);

        FlightDto result = flightService.createFlight(CREATE_FLIGHT_REQUEST, "ADMIN");

        assertNotNull(result);
        assertEquals(FLIGHT_DTO.getId(), result.getId());
        assertEquals(FLIGHT_DTO.getDepartureDateTime(), result.getDepartureDateTime());

        verify(flightMapper).toFlightEntity(CREATE_FLIGHT_REQUEST);
        verify(flightRepository).save(FLIGHT_ENTITY);
        verify(flightMapper).toFlightDto(FLIGHT_ENTITY);
    }

    @Test
    void getAllFlight_ShouldReturnSuccess() {
        when(flightRepository.findByDepartureDateTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn((FLIGHT_ENTITIES));
        when(flightMapper.toFlightDto(any(FlightEntity.class))).thenReturn(FLIGHT_DTO);

        List<FlightDto> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("AB123", result.getFirst().getFlightNumber());

        verify(flightRepository).findByDepartureDateTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class));
        verify(flightMapper, times(1)).toFlightDto(any(FlightEntity.class));

    }

    @Test
    void getFlightById_ShouldReturnSuccess() {
        when(flightRepository.findById(FLIGHT_ID)).thenReturn(Optional.of(FLIGHT_ENTITY));
        when(flightMapper.toFlightDto(FLIGHT_ENTITY)).thenReturn(FLIGHT_DTO);

        FlightDto result = flightService.getFlightById(FLIGHT_ID);

        assertNotNull(result);
        assertEquals(FLIGHT_ID, result.getId());
        assertEquals("AB123", result.getFlightNumber());

        verify(flightRepository).findById(FLIGHT_ID);
        verify(flightMapper).toFlightDto(FLIGHT_ENTITY);

    }

    @Test
    void updateFlight_ShouldReturnSuccess() {
        when(flightRepository.findById(FLIGHT_ID)).thenReturn(Optional.of(FLIGHT_ENTITY));
        when(flightRepository.save(any(FlightEntity.class))).thenReturn(FLIGHT_ENTITY);
        when(flightMapper.toFlightDto(any(FlightEntity.class))).thenReturn(FLIGHT_DTO);
        when(flightMapper.toFlightEntity(any(UpdateFlightRequest.class),eq(FLIGHT_ENTITY))).thenReturn(FLIGHT_ENTITY);

        FlightDto result = flightService.updateFlight(FLIGHT_ID, UPDATE_FLIGHT_REQUEST, ROLE);

        assertNotNull(result);
        assertEquals(FLIGHT_ID, result.getId());
        assertEquals("AB123", result.getFlightNumber());
        assertEquals(100, result.getAvailableSeats());

        verify(flightRepository).findById(FLIGHT_ID);
        verify(flightRepository).save(any(FlightEntity.class));
        verify(flightMapper).toFlightDto(any(FlightEntity.class));
        verify(flightMapper).toFlightEntity(any(UpdateFlightRequest.class),eq(FLIGHT_ENTITY));

    }

    @Test
    void updateFlightById_ShouldUserNotFound() {
        when(flightRepository.findById(NON_EXISTING_FLIGHT_ID)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> flightService.updateFlight(NON_EXISTING_FLIGHT_ID, UPDATE_FLIGHT_REQUEST, "ADMIN"));
        assertTrue(exception.getMessage().contains("No flight found with id: " + NON_EXISTING_FLIGHT_ID));
    }

}
