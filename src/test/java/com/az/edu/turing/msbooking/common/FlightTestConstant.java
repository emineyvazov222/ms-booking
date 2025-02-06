package com.az.edu.turing.msbooking.common;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.model.enums.City;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightTestConstant {

    Long FLIGHT_ID = 1L;
    Long NON_EXISTING_FLIGHT_ID = 999L;
    String FLIGHT_NUMBER = "AB123";
    LocalDateTime DEPARTURE_DATE_TIME = LocalDateTime.of(2025, 5, 1, 10, 0);
    LocalDateTime ARRIVAL_DATE_TIME = LocalDateTime.of(2025, 5, 1, 14, 0);
    BigDecimal PRICE = BigDecimal.valueOf(199.99);
    Integer AVAILABLE_SEATS = 100;


    FlightEntity FLIGHT_ENTITY = FlightEntity.builder()
            .flightNumber(FLIGHT_NUMBER)
            .departureDateTime(DEPARTURE_DATE_TIME)
            .arrivalDateTime(ARRIVAL_DATE_TIME)
            .price(PRICE)
            .availableSeats(AVAILABLE_SEATS)
            .arrivalCity(City.BAKU)
            .departureCity(City.LONDON)
            .flightStatus(FlightStatus.AVAILABLE)
            .build();


    List<FlightEntity> FLIGHT_ENTITIES = List.of(FLIGHT_ENTITY);


    FlightDto FLIGHT_DTO = FlightDto.builder()
            .id(FLIGHT_ID)
            .flightNumber(FLIGHT_NUMBER)
            .departureDateTime(DEPARTURE_DATE_TIME)
            .arrivalDateTime(ARRIVAL_DATE_TIME)
            .price(PRICE)
            .availableSeats(AVAILABLE_SEATS)
            .arrivalCity(City.BAKU)
            .departureCity(City.LONDON)
            .flightStatus(FlightStatus.AVAILABLE)
            .build();

    CreateFlightRequest CREATE_FLIGHT_REQUEST = CreateFlightRequest.builder()
            .flightNumber(FLIGHT_NUMBER)
            .departureDateTime(DEPARTURE_DATE_TIME)
            .arrivalDateTime(ARRIVAL_DATE_TIME)
            .price(PRICE)
            .availableSeats(AVAILABLE_SEATS)
            .arrivalCity(City.BAKU)
            .departureCity(City.LONDON)
            .flightStatus(FlightStatus.AVAILABLE)
            .build();

    UpdateFlightRequest UPDATE_FLIGHT_REQUEST = UpdateFlightRequest.builder()
            .flightNumber(FLIGHT_NUMBER)
            .departureDateTime(DEPARTURE_DATE_TIME)
            .arrivalDateTime(ARRIVAL_DATE_TIME)
            .price(PRICE)
            .availableSeats(AVAILABLE_SEATS)
            .arrivalCity(City.BAKU)
            .departureCity(City.LONDON)
            .flightStatus(FlightStatus.AVAILABLE)
            .build();

}
