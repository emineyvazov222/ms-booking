package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(CreateBookingRequest createBookingRequest);

    List<BookingDto> getAllBookings();

    BookingDto getBookingById(Long bookingId);

    BookingDto updateBooking(Long id, UpdateBookingRequest updateBookingRequest);

    void deleteBookingById(Long id);


}
