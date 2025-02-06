package com.az.edu.turing.msbooking.common;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import com.az.edu.turing.msbooking.model.enums.PaymentStatus;
import com.az.edu.turing.msbooking.model.enums.RoomType;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingTestConstant {

    Long BOOKING_ID = 1L;
    LocalDateTime TEST_BOOKING_DATE = LocalDateTime.of(2025, 1, 1, 12, 0);
    String TEST_SEAT_NUMBER = "12A";
    BookingStatus TEST_BOOKING_STATUS = BookingStatus.CONFIRMED;
    PaymentStatus TEST_PAYMENT_STATUS = PaymentStatus.PAID;
    RoomType TEST_ROOM_TYPE = RoomType.SINGLE;


    BookingEntity BOOKING_ENTITY = BookingEntity.builder()
            .bookingDate(TEST_BOOKING_DATE)
            .seatNumber(TEST_SEAT_NUMBER)
            .roomType(TEST_ROOM_TYPE)
            .paymentStatus(TEST_PAYMENT_STATUS)
            .bookingStatus(TEST_BOOKING_STATUS)
            .build();

    List<BookingEntity> BOOKING_ENTITIES = List.of(BOOKING_ENTITY);


    BookingDto BOOKING_DTO = BookingDto.builder()
            .id(BOOKING_ID)
            .bookingDate(TEST_BOOKING_DATE)
            .seatNumber(TEST_SEAT_NUMBER)
            .roomType(TEST_ROOM_TYPE)
            .paymentStatus(TEST_PAYMENT_STATUS)
            .bookingStatus(TEST_BOOKING_STATUS)
            .build();

    CreateBookingRequest CREATE_BOOKING_REQUEST = CreateBookingRequest.builder()
            .userId(1L)
            .flightId(1L)
            .seatNumber(TEST_SEAT_NUMBER)
            .roomType(TEST_ROOM_TYPE)
            .paymentStatus(TEST_PAYMENT_STATUS)
            .bookingStatus(TEST_BOOKING_STATUS)
            .build();


    UpdateBookingRequest UPDATE_BOOKING_REQUEST = UpdateBookingRequest.builder()
            .seatNumber(TEST_SEAT_NUMBER)
            .roomType(TEST_ROOM_TYPE)
            .paymentStatus(TEST_PAYMENT_STATUS)
            .bookingStatus(TEST_BOOKING_STATUS)
            .build();

}
