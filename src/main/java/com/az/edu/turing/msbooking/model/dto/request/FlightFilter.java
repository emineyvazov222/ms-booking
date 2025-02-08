package com.az.edu.turing.msbooking.model.dto.request;

import com.az.edu.turing.msbooking.model.enums.City;
import jakarta.validation.constraints.Min;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@ParameterObject
public record FlightFilter(

        String flightNumber,

        City departureCity,

        City arrivalCity,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate departureDateTime,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate arrivalDateTime,

        @Min(0)
        Integer availableSeats,

        @Min(0)
        Double maxPrice
) {
}
