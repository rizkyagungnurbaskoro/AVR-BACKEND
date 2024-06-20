package com.csc3402.lab.avr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @NotBlank(message = "Cardholder name is required")
    @Column(name = "cardholder_name")
    private String cardholderName;

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    @Column(name = "card_number")
    private String cardNumber;

    @NotBlank(message = "Expiration date is required")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Expiration date must be in MM/YY format")
    @Column(name = "exp_date")
    private String expDate;

    @NotBlank(message = "CVC is required")
    @Pattern(regexp = "\\d{3}", message = "CVC must be 3 digits")
    @Column(name = "cvc")
    private String cvc;

    @NotNull(message = "Total price is required")
    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Payment() {}

    public Payment(String cardholderName, String cardNumber, String expDate, String cvc, Double totalPrice, Date paymentDate, Booking booking) {
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvc = cvc;
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

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
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
