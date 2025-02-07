package com.az.edu.turing.msbooking.service;


import com.az.edu.turing.msbooking.domain.repository.BookingRepository;
import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.exception.UnauthorizedException;
import com.az.edu.turing.msbooking.mapper.BookingMapper;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.az.edu.turing.msbooking.common.BookingTestConstant.*;
import static com.az.edu.turing.msbooking.common.FlightTestConstant.FLIGHT_ENTITY;
import static com.az.edu.turing.msbooking.common.UserTestConstant.USER_ENTITY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class BookingServiceTest {

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void createBooking_ShouldReturnSuccess() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(FLIGHT_ENTITY));
        when(userRepository.findById(1L)).thenReturn(Optional.of(USER_ENTITY));
        when(bookingMapper.toBookingEntity(CREATE_BOOKING_REQUEST, FLIGHT_ENTITY, USER_ENTITY)).thenReturn(BOOKING_ENTITY);
        when(bookingRepository.save(any())).thenReturn(BOOKING_ENTITY);
        when(bookingMapper.toBookingDto(any())).thenReturn(BOOKING_DTO);

        BookingDto result = bookingService.createBooking(CREATE_BOOKING_REQUEST, "ADMIN");

        assertNotNull(result);
        assertEquals(BOOKING_DTO, result);
    }

    @Test
    void createBooking_ShouldThrowNotFoundException() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> bookingService.createBooking(CREATE_BOOKING_REQUEST, "ADMIN"));
    }

    @Test
    void createBooking_ShouldThrowNotFoundException_WhenUserDoesNotExist() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(FLIGHT_ENTITY));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> bookingService.createBooking(CREATE_BOOKING_REQUEST, "ADMIN"));
    }

    @Test
    void getAllBookings_ShouldReturnListOfBookings_WhenRoleIsAdmin() {
        when(bookingRepository.findAll()).thenReturn(List.of(BOOKING_ENTITY));
        when(bookingMapper.toBookingDto(any())).thenReturn(BOOKING_DTO);

        List<BookingDto> result = bookingService.getAllBookings("ADMIN");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(BOOKING_DTO, result.getFirst());
    }

    @Test
    void getAllBookings_ShouldThrowUnauthorizedException_WhenRoleIsNotAdmin() {
        assertThrows(UnauthorizedException.class, () -> bookingService.getAllBookings("USER"));
    }

    @Test
    void getBookingById_ShouldReturnSuccess() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(BOOKING_ENTITY));
        when(bookingMapper.toBookingDto(BOOKING_ENTITY)).thenReturn(BOOKING_DTO);

        BookingDto result = bookingService.getBookingById(1L);

        assertNotNull(result);
        assertEquals(BOOKING_DTO, result);
    }

    @Test
    void deleteBookingById_ShouldSetBookingStatusToCancelled_WhenBookingExists() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(BOOKING_ENTITY));

        bookingService.deleteBookingById(1L, "ADMIN");

        assertEquals(BookingStatus.CANCELLED, BOOKING_ENTITY.getBookingStatus());
        verify(bookingRepository).save(BOOKING_ENTITY);
    }

    @Test
    void deleteBookingById_ShouldThrowNotFoundException_WhenBookingDoesNotExist() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> bookingService.deleteBookingById(1L, "ADMIN"));
    }

    @Test
    void deleteBookingById_ShouldThrowUnauthorizedException_WhenRoleIsNotAdmin() {
        assertThrows(UnauthorizedException.class,
                () -> bookingService.deleteBookingById(1L, "USER"));
    }

}
