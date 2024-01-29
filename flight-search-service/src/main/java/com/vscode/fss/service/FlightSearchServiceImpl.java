package com.vscode.fss.service;

import com.vscode.fss.entity.Flight;
import com.vscode.fss.model.FlightSearchRequest;
import com.vscode.fss.model.FlightSearchResponse;
import com.vscode.fss.repository.FlightSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightSearchRepository flightSearchRepository;

    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest) {
        List<Flight> flights = flightSearchRepository
                .findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(
                        flightSearchRequest.origin(),
                        flightSearchRequest.destination(),
                        flightSearchRequest.travelDate(),
                        flightSearchRequest.passengers());

        return flights
                .stream()
                .map(this::mapToFlightSearchResponse)
                .collect(Collectors.toList());
    }

    private FlightSearchResponse mapToFlightSearchResponse(Flight flight) {
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        BeanUtils.copyProperties(flight, flightSearchResponse);
        return flightSearchResponse;
    }
}
