package com.csc3402.lab.avr.repository;

import com.csc3402.lab.avr.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByBooking_BookingId(Integer bookingId);
}
