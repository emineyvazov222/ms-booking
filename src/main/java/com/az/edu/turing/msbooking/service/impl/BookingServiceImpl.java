package com.az.edu.turing.msbooking.service.impl;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.domain.repository.BookingRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.BookingMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import com.az.edu.turing.msbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto createBooking(CreateBookingRequest createBookingRequest) {
        BookingEntity bookingEntity = bookingRepository.save(bookingMapper.toBookingEntity(createBookingRequest));
        return bookingMapper.toBookingDto(bookingEntity);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingDto).collect(Collectors.toList());
    }

    @Override
    public BookingDto getBookingById(Long bookingId) {
        BookingEntity bookingById = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with specified id not found"));
        return bookingMapper.toBookingDto(bookingById);
    }

    @Override
    public BookingDto updateBooking(Long id, UpdateBookingRequest updateBookingRequest) {
        BookingEntity bookingEntity = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with specified id not found"));
        bookingEntity.setBookingDate(updateBookingRequest.getBookingDate());
        bookingEntity.setSeatNumber(updateBookingRequest.getSeatNumber());
        bookingEntity.setBookingStatus(updateBookingRequest.getBookingStatus());
        bookingEntity.setPaymentStatus(updateBookingRequest.getPaymentStatus());
        bookingEntity.setRoomType(updateBookingRequest.getRoomType());
        return bookingMapper.toBookingDto(bookingRepository.save(bookingEntity));

    }

    @Override
    public void deleteBookingById(Long id) {
        BookingEntity bookingById = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with specified id not found"));
        bookingById.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(bookingById);

    }
}
