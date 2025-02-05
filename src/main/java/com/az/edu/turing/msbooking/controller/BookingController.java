package com.az.edu.turing.msbooking.controller;

import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import com.az.edu.turing.msbooking.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> create(@Valid @RequestBody CreateBookingRequest createBookingRequest, @RequestHeader String role) {
        return ResponseEntity.ok(bookingService.createBooking(createBookingRequest, role));
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getBookings(@RequestHeader String role) {
        return ResponseEntity.ok(bookingService.getAllBookings(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getFlightById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> update(
            @Min(1) @NotNull @PathVariable Long id,
            @Valid @RequestBody UpdateBookingRequest updateBookingRequest, @RequestHeader String role) {
        return ResponseEntity.ok(bookingService.updateBooking(id, updateBookingRequest, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(1) @NotNull @PathVariable("id") Long id, @RequestHeader String role) {
        bookingService.deleteBookingById(id, role);
        return ResponseEntity.noContent().build();
    }

}
