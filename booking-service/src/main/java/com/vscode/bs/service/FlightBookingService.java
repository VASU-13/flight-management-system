package com.vscode.bs.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vscode.bs.entity.BookingStatus;
import com.vscode.bs.entity.FlightBooking;
import com.vscode.bs.model.BookingRequest;
import com.vscode.bs.model.BookingResponse;
import com.vscode.bs.model.FlightBookingRequest;
import com.vscode.bs.model.FlightBookingResponse;
import com.vscode.bs.repository.FlightBookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("flightBookingService")
public class FlightBookingService implements BookingService {

    private final FlightBookingRepository flightBookingRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        if (!(bookingRequest instanceof FlightBookingRequest)) {
            throw new IllegalArgumentException("Invalid booking type");
        }

        FlightBooking flightBooking = mapToFlightBooking(bookingRequest);

        // validate flight Booking

        flightBooking = flightBookingRepository.save(flightBooking);

        FlightBookingResponse flightBookingResponse = new FlightBookingResponse();
        BeanUtils.copyProperties(flightBooking, flightBookingResponse);

        return flightBookingResponse;
    }

    private FlightBooking mapToFlightBooking(BookingRequest bookingRequest) {
        FlightBookingRequest flightBookingRequest = (FlightBookingRequest) bookingRequest;

        FlightBooking flightBooking = new FlightBooking();

        flightBooking.setBookingNumber(UUID.randomUUID().toString());
        flightBooking.setFlightNumber(flightBookingRequest.getFlightNumber());

        flightBooking.setBookingDate(LocalDate.now());
        flightBooking.setPassengerName(flightBookingRequest.getPassengerName());

        flightBooking.setAmount(flightBookingRequest.getAmount());
        flightBooking.setPaymentMode(flightBookingRequest.getPaymentMode().name());
        flightBooking.setStatus(BookingStatus.CREATED.name());

        return flightBooking;

    }
}
