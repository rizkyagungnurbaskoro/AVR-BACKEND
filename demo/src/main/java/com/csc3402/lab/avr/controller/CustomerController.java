package com.csc3402.lab.avr.controller;

import com.csc3402.lab.avr.model.Customer;
import com.csc3402.lab.avr.repository.CustomerRepository;
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

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final CustomerRepository customerRepository;

    public CustomerController(BookingService bookingService, CustomerRepository customerRepository, RoomService roomService) {
        this.bookingService = bookingService;
        this.customerRepository = customerRepository;
        this.roomService = roomService;
    }

    @GetMapping("list")
    public String showCustomerList(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "list-customer";
    }

    @GetMapping("signup")
    public String showSignUpForm(Customer customer) {
        return "add-customer";
    }

    @PostMapping("add")
    public String addCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-customer";
        }
        customerRepository.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Customer customer = customerRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @PostMapping("update/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setCustid((int) id);
            return "update-customer";
        }
        customerRepository.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id, Model model) {
        Customer customer = customerRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        customerRepository.delete(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/")
    public String home(Model model) {
        // Add necessary attributes to the model
        model.addAttribute("rooms", roomService.listAllRooms());
        return "index";
    }

}
