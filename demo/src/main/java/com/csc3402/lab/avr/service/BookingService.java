package com.csc3402.lab.avr.service;

import com.csc3402.lab.avr.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> listAllBookings();
}