package com.csc3402.lab.avr.controller;

import com.csc3402.lab.avr.model.*;
import com.csc3402.lab.avr.repository.*;
import com.csc3402.lab.avr.service.BookingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

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
    public String checkout(@RequestParam("selectedRoom") String selectedRoom,
                           @RequestParam("checkin") String checkin,
                           @RequestParam("checkout") String checkout,
                           @RequestParam("counterValueAdult") int counterValueAdult,
                           @RequestParam("counterValueChild") int counterValueChild,
                           Model model) {
        Room room = roomRepository.findByRoomType(selectedRoom);
        if (room == null) {
            model.addAttribute("errorMessage", "Invalid room type selected");
            return "error";
        }

        LocalDate checkinDate = LocalDate.parse(checkin);
        LocalDate checkoutDate = LocalDate.parse(checkout);
        long daysBetween = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        double totalPrice = room.getPrice() * daysBetween;

        model.addAttribute("selectedRoom", selectedRoom);
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("counterValueAdult", counterValueAdult);
        model.addAttribute("counterValueChild", counterValueChild);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("payment", new Payment());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String addPayment(@Valid Payment payment, BindingResult result, Model model,
                             @RequestParam("selectedRoom") String selectedRoom,
                             @RequestParam("checkin") String checkin,
                             @RequestParam("checkout") String checkout) {
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

        Booking booking = new Booking();
        booking.setStart(Date.from(checkinDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        booking.setEndDate(Date.from(checkoutDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        booking.setNotes(payment.getCardholderName()); // Using cardholder name as guest name
        booking.setStatus("Confirmed");
        booking.setRoomType(selectedRoom);  // Assuming this field exists in Booking
        bookingRepository.save(booking);

        payment.setBooking(booking);
        paymentRepository.save(payment);

        return "redirect:/confirmation?bookingId=" + booking.getBookingId();
    }

    @GetMapping("/confirmation")
    public String confirmation(@RequestParam Integer bookingId, Model model) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            model.addAttribute("errorMessage", "Booking not found");
            return "error";
        }

        // Assuming that you want to get the first customer
        Customer customer = booking.getCustomers().stream().findFirst().orElse(null);
        model.addAttribute("booking", booking);
        model.addAttribute("customer", customer);
        return "bookingconfirmation";
    }
}