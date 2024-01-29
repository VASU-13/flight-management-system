package com.vscode.fss.service;

import com.vscode.fss.model.FlightSearchRequest;
import com.vscode.fss.model.FlightSearchResponse;

import java.util.List;

public interface FlightSearchService {

    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest);
}