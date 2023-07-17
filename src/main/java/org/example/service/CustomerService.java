package org.example.service;

import org.example.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer login(String customerName, String password);

    void register(Customer customer);

    boolean validateCustomer(Customer customer);

    List<Customer> getAll();
}