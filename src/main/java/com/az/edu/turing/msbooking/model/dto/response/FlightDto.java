package com.az.edu.turing.msbooking.model.dto.response;

import com.az.edu.turing.msbooking.model.enums.City;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;
    private String flightNumber;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private FlightStatus status;
    private BigDecimal price;
    private Integer availableSeats;
    private City departureCity;
    private City arrivalCity;

}
