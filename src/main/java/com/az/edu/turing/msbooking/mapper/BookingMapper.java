package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

//    @Mapping(target = "id", ignore = true)
    BookingEntity toBookingEntity(CreateBookingRequest createBookingRequest);

//    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    BookingEntity toBookingEntity(UpdateBookingRequest updateBookingRequest);

    BookingDto toBookingDto(BookingEntity bookingEntity);

}