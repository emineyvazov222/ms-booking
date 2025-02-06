package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightEntity toFlightEntity(CreateFlightRequest request){
        return FlightEntity.builder()
                .flightNumber(request.getFlightNumber())
                .flightStatus(request.getFlightStatus())
                .price(request.getPrice())
                .arrivalCity(request.getArrivalCity())
                .departureCity(request.getDepartureCity())
                .availableSeats(request.getAvailableSeats())
                .departureDateTime(request.getDepartureDateTime())
                .arrivalDateTime(request.getArrivalDateTime())
                .build();
    }

    public FlightDto toFlightDto(FlightEntity entity){
        return FlightDto.builder()
                .id(entity.getId())
                .flightNumber(entity.getFlightNumber())
                .flightStatus(entity.getFlightStatus())
                .price(entity.getPrice())
                .arrivalCity(entity.getArrivalCity())
                .departureCity(entity.getDepartureCity())
                .availableSeats(entity.getAvailableSeats())
                .departureDateTime(entity.getDepartureDateTime())
                .arrivalDateTime(entity.getArrivalDateTime())
                .build();
    }

    public FlightEntity toFlightEntity(UpdateFlightRequest request){
        return FlightEntity.builder()
                .flightNumber(request.getFlightNumber())
                .flightStatus(request.getFlightStatus())
                .price(request.getPrice())
                .arrivalCity(request.getArrivalCity())
                .departureCity(request.getDepartureCity())
                .availableSeats(request.getAvailableSeats())
                .departureDateTime(request.getDepartureDateTime())
                .arrivalDateTime(request.getArrivalDateTime())
                .build();
    }

    public FlightEntity toFlightEntity(UpdateFlightRequest request, FlightEntity entity) {
        entity.setAvailableSeats(request.getAvailableSeats());
        entity.setDepartureDateTime(request.getDepartureDateTime());
        entity.setArrivalDateTime(request.getArrivalDateTime());
        entity.setFlightStatus(request.getFlightStatus());
        entity.setPrice(request.getPrice());
        entity.setArrivalCity(request.getArrivalCity());
        entity.setDepartureCity(request.getDepartureCity());
        return entity;
    }
}
