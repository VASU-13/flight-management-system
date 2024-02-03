package com.vscode.bs.model;

import lombok.Data;

@Data
public final class FlightBookingResponse extends BookingResponse {
    private String flightNumber;
}
