package com.az.edu.turing.msbooking.model.dto.response;

import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import com.az.edu.turing.msbooking.model.enums.City;
import com.az.edu.turing.msbooking.model.enums.PaymentStatus;
import com.az.edu.turing.msbooking.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Long id;
    private Long userId;
    private Long flightId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String seatNumber;
    private RoomType roomType;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime bookingDate;
    private String flightNumber;
    private City departureCity;
    private City arrivalCity;

}
