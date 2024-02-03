package com.vscode.bs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vscode.bs.entity.HotelBooking;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

}
