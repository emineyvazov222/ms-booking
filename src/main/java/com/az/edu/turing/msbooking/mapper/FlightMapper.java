package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "arrivalCity", source = "arrivalCity")
    FlightEntity toFlightEntity(CreateFlightRequest createFlightRequest);

    FlightEntity toFlightEntity(UpdateFlightRequest updateFlightRequest);

//    @Mapping(target = "flightStatus", source = "flightStatus")
    FlightDto toFlightDto(FlightEntity flightEntity);

}
