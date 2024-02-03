package com.vscode.bs.repository;

import com.vscode.bs.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

}
