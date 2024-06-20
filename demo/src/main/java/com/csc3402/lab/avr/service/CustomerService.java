package com.csc3402.lab.avr.service;

import com.csc3402.lab.avr.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> listAllCustomers();
    Customer addNewCustomer(Customer customer);
    Optional<Customer> findCustomerById(Integer custid);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
}