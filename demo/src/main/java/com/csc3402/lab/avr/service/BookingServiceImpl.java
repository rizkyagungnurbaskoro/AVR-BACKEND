package com.csc3402.lab.avr.service;

import com.csc3402.lab.avr.model.Booking;
import com.csc3402.lab.avr.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> listAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }
}