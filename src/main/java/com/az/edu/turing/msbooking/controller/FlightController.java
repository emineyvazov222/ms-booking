package com.az.edu.turing.msbooking.controller;

import com.az.edu.turing.msbooking.model.dto.request.CreateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateFlightRequest;
import com.az.edu.turing.msbooking.model.dto.response.FlightDto;
import com.az.edu.turing.msbooking.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
@Validated
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightDto> create(@Valid @RequestBody CreateFlightRequest createFlightRequest, @RequestHeader String role) {
        return ResponseEntity.ok(flightService.createFlight(createFlightRequest, role));
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> update(
            @Min(1) @NotNull @PathVariable Long id,
            @Valid @RequestBody UpdateFlightRequest updateFlightRequest, @RequestHeader String role) {
        return ResponseEntity.ok(flightService.updateFlight(id, updateFlightRequest, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(1) @NotNull @PathVariable("id") Long flightId, @RequestHeader String role) {
        flightService.deleteFlight(flightId, role);
        return ResponseEntity.noContent().build();
    }

}
