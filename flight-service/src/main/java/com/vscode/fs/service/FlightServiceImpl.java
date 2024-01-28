package com.vscode.fs.service;

import com.vscode.fs.entity.Flight;
import com.vscode.fs.exception.FlightServiceCustomException;
import com.vscode.fs.model.FlightRequest;
import com.vscode.fs.model.FlightResponse;
import com.vscode.fs.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private final FlightRepository flightRepository;

    @Override
    public FlightResponse createFlight(FlightRequest flightRequest) {

        Flight flight = Flight.builder()
                .flightNumber(flightRequest.flightNumber())
                .origin(flightRequest.origin())
                .destination(flightRequest.destination())
                .departureDate(flightRequest.departureDate())
                .arrivalDate(flightRequest.arrivalDate())
                .totalSeats(flightRequest.totalSeats())
                .availableSeats(flightRequest.availableSeats())
                .amount(flightRequest.amount())
                .build();

        flightRepository.save(flight);

        FlightResponse flightResponse = new FlightResponse();
        BeanUtils.copyProperties(flight,flightResponse);
        log.info("Flight created {}", flightResponse.getFlightId());
        return flightResponse;

    }

    @Override
    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights
                .stream().map(this::mapToFlightResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FlightResponse getFlightByNumber(String flightNumber) {
        log.info("Get the flight for flight number: {}", flightNumber);
        Flight optionalFlight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(
                        () -> new FlightServiceCustomException("Flight with given number not found",
                                "FLIGHT_NOT_FOUND"));

        FlightResponse flightResponse = new FlightResponse();
        BeanUtils.copyProperties(optionalFlight, flightResponse);
        return flightResponse;
    }


    private FlightResponse mapToFlightResponse(Flight flight) {
        FlightResponse flightResponse = new FlightResponse();
        BeanUtils.copyProperties(flight,flightResponse);
        return flightResponse;
    }


}
