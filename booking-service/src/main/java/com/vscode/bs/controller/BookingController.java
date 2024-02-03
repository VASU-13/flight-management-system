package com.vscode.bs.controller;

import com.vscode.bs.model.BookingResponse;
import com.vscode.bs.model.FlightBookingRequest;
import com.vscode.bs.model.HotelBookingRequest;
import com.vscode.bs.service.BookingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/v1/api/bookings")
@Log4j2
public class BookingController {

    private final BookingService bookingService;

    private final BookingService hotelBookingService;

    public BookingController(@Qualifier("flightBookingService") BookingService bookingService,
            BookingService hotelBookingService) {
        this.bookingService = bookingService;
        this.hotelBookingService = hotelBookingService;
    }

    @PostMapping("/flight")
    public BookingResponse createFlightBooking(@RequestBody FlightBookingRequest flightBookingRequest) {
        log.info("save {} ", flightBookingRequest.getFlightNumber());
        return bookingService.createBooking(flightBookingRequest);
    }

    @PostMapping("/hotel")
    public BookingResponse createHotelBooking(@RequestBody HotelBookingRequest hotelBookingRequest) {
        log.info("save {} ", hotelBookingRequest.getHotelName());
        return hotelBookingService.createBooking(hotelBookingRequest);
    }

}
