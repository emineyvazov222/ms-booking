package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingEntity toBookingEntity(CreateBookingRequest createBookingRequest) {
        return BookingEntity.builder()
                .bookingDate(createBookingRequest.getBookingDate())
                .paymentStatus(createBookingRequest.getPaymentStatus())
                .roomType(createBookingRequest.getRoomType())
                .seatNumber(createBookingRequest.getSeatNumber())
                .bookingStatus(createBookingRequest.getBookingStatus())
                .build();
    }

    public BookingDto toBookingDto(BookingEntity bookingEntity) {
        return BookingDto.builder()
                .id(bookingEntity.getId())
                .bookingDate(bookingEntity.getBookingDate())
                .paymentStatus(bookingEntity.getPaymentStatus())
                .roomType(bookingEntity.getRoomType())
                .seatNumber(bookingEntity.getSeatNumber())
                .bookingStatus(bookingEntity.getBookingStatus())
                .build();
    }
}
