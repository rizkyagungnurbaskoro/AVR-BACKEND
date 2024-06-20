package com.csc3402.lab.avr.controller;

import com.csc3402.lab.avr.model.Customer;
import com.csc3402.lab.avr.model.Payment;
import com.csc3402.lab.avr.repository.CustomerRepository;
import com.csc3402.lab.avr.repository.PaymentRepository;
import com.csc3402.lab.avr.service.BookingService;
import com.csc3402.lab.avr.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/")
public class CustomerController {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;

    public CustomerController(BookingService bookingService, CustomerRepository customerRepository, RoomService roomService, PaymentRepository paymentRepository) {
        this.bookingService = bookingService;
        this.customerRepository = customerRepository;
        this.roomService = roomService;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("rooms", roomService.listAllRooms());
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
        return "checkout";
    }

    @PostMapping("/checkout")
    public String addPayment(@Valid Payment payment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "checkout";
        }
        payment.setPaymentDate(new Date()); // Set payment date
        payment.setTotalPrice(550.00); // Set total price, or fetch this dynamically
        paymentRepository.save(payment);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "bookingconfirmation";
    }
}
