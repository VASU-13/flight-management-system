package com.vscode.fs.service;

import com.vscode.fs.model.FlightRequest;
import com.vscode.fs.model.FlightResponse;

import java.util.List;

public interface FlightService {

    FlightResponse createFlight(FlightRequest flightRequest);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightByNumber(String flightNumber);
}
