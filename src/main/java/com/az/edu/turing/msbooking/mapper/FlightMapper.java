package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightEntity toFlightEntity(CreateFlightRequest createFlightRequest) {
        return FlightEntity.builder()
                .flightNumber(createFlightRequest.getFlightNumber())
                .departureDateTime(createFlightRequest.getDepartureDateTime())
                .arrivalDateTime(createFlightRequest.getArrivalDateTime())
                .arrivalCity(createFlightRequest.getArrivalCity())
                .departureCity(createFlightRequest.getDepartureCity())
                .availableSeats(createFlightRequest.getAvailableSeats())
                .price(createFlightRequest.getPrice())
                .status(createFlightRequest.getStatus())
                .build();
    }

    public FlightDto toFlightDto(FlightEntity flightEntity) {
        return FlightDto.builder()
                .flightNumber(flightEntity.getFlightNumber())
                .departureDateTime(flightEntity.getDepartureDateTime())
                .arrivalDateTime(flightEntity.getArrivalDateTime())
                .arrivalCity(flightEntity.getArrivalCity())
                .departureCity(flightEntity.getDepartureCity())
                .availableSeats(flightEntity.getAvailableSeats())
                .price(flightEntity.getPrice())
                .status(flightEntity.getStatus())
                .build();
    }


}
