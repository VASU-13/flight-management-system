package com.vscode.bs.service;

import com.vscode.bs.model.BookingRequest;
import com.vscode.bs.model.BookingResponse;

public interface BookingService {

    public BookingResponse createBooking(BookingRequest bookingRequest);

}
