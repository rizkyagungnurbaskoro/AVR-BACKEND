package com.csc3402.lab.avr.controller;

import com.csc3402.lab.avr.model.*;
import com.csc3402.lab.avr.repository.CustomerRepository;
import com.csc3402.lab.avr.repository.PaymentRepository;
import com.csc3402.lab.avr.repository.RoomRepository;
import com.csc3402.lab.avr.repository.BookingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "index";
    }

    @GetMapping("/customers/list")
    public String showCustomerList(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "list-customer";
    }

    @GetMapping("/customers/signup")
    public String showSignUpForm(Customer customer) {
        return "register";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        customerRepository.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/customers/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Customer customer = customerRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @PostMapping("/customers/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setCustid((int) id);
            return "update-customer";
        }
        customerRepository.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id, Model model) {
        Customer customer = customerRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        customerRepository.delete(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("payment", new Payment());
        return "checkout"; // Ensure this matches the template name
    }

    @PostMapping("/checkout")
    public String addPayment(@Valid Payment payment, BindingResult result, Model model, @RequestParam("selectedRoom") String selectedRoom, @RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout) {
        if (result.hasErrors()) {
            return "checkout";
        }

        Room room = roomRepository.findByRoomType(selectedRoom);
        if (room == null) {
            result.rejectValue("roomType", "error.roomType", "Invalid room type selected");
            return "checkout";
        }

        LocalDate checkinDate = LocalDate.parse(checkin);
        LocalDate checkoutDate = LocalDate.parse(checkout);

        long daysBetween = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        double totalPrice = room.getPrice() * daysBetween;

        payment.setPaymentDate(new Date());
        payment.setTotalPrice(totalPrice);
        payment.setCheckinDate(Date.from(checkinDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        payment.setCheckoutDate(Date.from(checkoutDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        paymentRepository.save(payment);

        // Assuming you create a booking for the payment
        Booking booking = new Booking();
        booking.setStart(Date.from(checkinDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        booking.setEndDate(Date.from(checkoutDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        booking.setBookDate(new Date());
        booking.setNotes("Booking notes");
        booking.setStatus("Confirmed");
        booking = bookingRepository.save(booking);

        // Link the booking to the payment
        payment.setBooking(booking);
        paymentRepository.save(payment);

        return "redirect:/confirmation?bookingId=" + booking.getBookingId();
    }

    @GetMapping("/confirmation")
    public String confirmation(@RequestParam Integer bookingId, Model model) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id:" + bookingId));
        model.addAttribute("booking", booking);
        return "bookingconfirmation";
    }
}
