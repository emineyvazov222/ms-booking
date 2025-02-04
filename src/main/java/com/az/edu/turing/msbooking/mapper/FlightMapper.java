package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;
import com.az.edu.turing.msbooking.model.enums.Role;
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
                .flightStatus(createFlightRequest.getFlightStatus())
                .build();
    }

    public FlightDto toFlightDto(FlightEntity flightEntity) {
        return FlightDto.builder()
                .id(flightEntity.getId())
                .flightNumber(flightEntity.getFlightNumber())
                .departureDateTime(flightEntity.getDepartureDateTime())
                .arrivalDateTime(flightEntity.getArrivalDateTime())
                .arrivalCity(flightEntity.getArrivalCity())
                .departureCity(flightEntity.getDepartureCity())
                .availableSeats(flightEntity.getAvailableSeats())
                .price(flightEntity.getPrice())
                .flightStatus(FlightStatus.AVAILABLE)
                .build();
    }

    public FlightEntity toFlightEntity(UpdateFlightRequest updateFlightRequest) {
        return FlightEntity.builder()
                .flightNumber(updateFlightRequest.getFlightNumber())
                .departureDateTime(updateFlightRequest.getDepartureDateTime())
                .arrivalDateTime(updateFlightRequest.getArrivalDateTime())
                .arrivalCity(updateFlightRequest.getArrivalCity())
                .departureCity(updateFlightRequest.getDepartureCity())
                .availableSeats(updateFlightRequest.getAvailableSeats())
                .price(updateFlightRequest.getPrice())
                .flightStatus(updateFlightRequest.getFlightStatus())
                .build();
    }

}
