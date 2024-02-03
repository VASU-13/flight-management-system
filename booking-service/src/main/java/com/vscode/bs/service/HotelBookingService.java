package com.vscode.bs.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vscode.bs.entity.BookingStatus;
import com.vscode.bs.entity.HotelBooking;
import com.vscode.bs.model.BookingRequest;
import com.vscode.bs.model.BookingResponse;
import com.vscode.bs.model.HotelBookingRequest;
import com.vscode.bs.model.HotelBookingResponse;
import com.vscode.bs.repository.HotelBookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@Qualifier("hotelBookingService")
@RequiredArgsConstructor
public class HotelBookingService implements BookingService {

    private final HotelBookingRepository hotelBookingRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        if (!(bookingRequest instanceof HotelBookingRequest)) {
            throw new IllegalArgumentException("Invalid booking type");
        }

        HotelBooking hotelBooking = mapToHotelBooking(bookingRequest);

        // validate Hotel Booking

        hotelBooking = hotelBookingRepository.save(hotelBooking);

        HotelBookingResponse hotelBookingResponse = new HotelBookingResponse();
        BeanUtils.copyProperties(hotelBooking, hotelBookingResponse);

        return hotelBookingResponse;
    }

    private HotelBooking mapToHotelBooking(BookingRequest bookingRequest) {
        HotelBookingRequest hotelBookingRequest = (HotelBookingRequest) bookingRequest;

        HotelBooking hotelBooking = new HotelBooking();

        hotelBooking.setBookingNumber(UUID.randomUUID().toString());
        hotelBooking.setBookingDate(LocalDate.now());

        hotelBooking.setPassengerName(hotelBookingRequest.getPassengerName());
        hotelBooking.setAmount(hotelBookingRequest.getAmount());
        hotelBooking.setPaymentMode(hotelBookingRequest.getPaymentMode().name());
        hotelBooking.setStatus(BookingStatus.CREATED.name());

        hotelBooking.setHotelName(hotelBookingRequest.getHotelName());
        hotelBooking.setCheckInDate(hotelBookingRequest.getCheckInDate());
        hotelBooking.setCheckOutDate(hotelBookingRequest.getCheckOutDate());

        return hotelBooking;
    }

}
