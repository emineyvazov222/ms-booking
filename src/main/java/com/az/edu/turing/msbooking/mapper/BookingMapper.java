package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookingMapper {

    public BookingEntity toBookingEntity(CreateBookingRequest request, FlightEntity flight, UserEntity user) {

        return BookingEntity.builder()
                .flight(flight)
                .user(user)
                .bookingDate(LocalDateTime.now())
                .seatNumber(request.getSeatNumber())
                .bookingStatus(request.getBookingStatus())
                .paymentStatus(request.getPaymentStatus())
                .roomType(request.getRoomType())
                .build();
    }

    public BookingDto toBookingDto(BookingEntity booking) {

        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUser().getId())
                .flightId(booking.getFlight().getId())
                .firstName(booking.getUser().getFirstName())
                .lastName(booking.getUser().getLastName())
                .email(booking.getUser().getEmail())
                .phone(booking.getUser().getPhoneNumber())
                .seatNumber(booking.getSeatNumber())
                .bookingStatus(booking.getBookingStatus())
                .paymentStatus(booking.getPaymentStatus())
                .roomType(booking.getRoomType())
                .bookingDate(booking.getBookingDate())
                .departureDateTime(booking.getFlight().getDepartureDateTime())
                .arrivalDateTime(booking.getFlight().getArrivalDateTime())
                .flightNumber(booking.getFlight().getFlightNumber())
                .departureCity(booking.getFlight().getDepartureCity())
                .arrivalCity(booking.getFlight().getArrivalCity())
                .build();
    }

    public BookingEntity toBookingEntity(UpdateBookingRequest request, BookingEntity booking) {

        booking.setSeatNumber(request.getSeatNumber());
        booking.setBookingStatus(request.getBookingStatus());
        booking.setPaymentStatus(request.getPaymentStatus());
        booking.setRoomType(request.getRoomType());
        return booking;
    }
}
