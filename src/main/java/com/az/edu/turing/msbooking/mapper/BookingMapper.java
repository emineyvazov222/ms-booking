package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "bookingDate", source = "request.bookingDate")
    @Mapping(target = "seatNumber", source = "request.seatNumber")
    @Mapping(target = "bookingStatus", source = "request.bookingStatus")
    @Mapping(target = "paymentStatus", source = "request.paymentStatus")
    @Mapping(target = "roomType", source = "request.roomType")
    BookingEntity toBookingEntity(CreateBookingRequest request, FlightEntity flight, UserEntity user);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "flightNumber", source = "flight.flightNumber")
    @Mapping(target = "departureCity", source = "flight.departureCity")
    @Mapping(target = "arrivalCity", source = "flight.arrivalCity")
    BookingDto toBookingDto(BookingEntity bookingEntity);

//    @Mapping(target = "bookingDate", source = "bookingDate")
    BookingEntity toBookingEntity(UpdateBookingRequest updateBookingRequest);

}