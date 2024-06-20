package com.csc3402.lab.avr.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;



    public Payment() {}

    public Payment(Double totalPrice, Date paymentDate, Booking booking) {
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
        this.booking = booking;
    }

    // Getters and Setters
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}