package com.csc3402.lab.avr.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "start")
    private Date start;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "book_date")
    private Date bookDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<Customer> customers;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    // Getters and Setters

    public Booking() {
    }


    public Booking( Date start, Date endDate, Date bookDate, String notes, String status) {
        this.start = start;
        this.endDate = endDate;
        this.bookDate = bookDate;
        this.notes = notes;
        this.status = status;
    }


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", start=" + start +
                ", endDate=" + endDate +
                ", bookDate=" + bookDate +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}