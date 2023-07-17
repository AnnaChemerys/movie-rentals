package org.example.service;

import org.example.dao.CustomerDao;
import org.example.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    public CustomerServiceImpl() {
        this.customerDao = new CustomerDao();
    }

    @Override
    public Customer login(String customerName, String password) {
        List<Customer>customers = customerDao.getAll();
        return customers.stream().filter(existedCustomer -> existedCustomer.getLogin().equals(customerName)
                && existedCustomer.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public void register(Customer customer) {
        if (validateCustomer(customer)) {
            customerDao.save(customer);
        } else {
            System.out.println("Failed to register user. User with login " + customer.getLogin() + " already exists!");
        }
    }

    @Override
    public boolean validateCustomer(Customer customer) {
        List<Customer> customers = customerDao.getAll();
        return customers.stream().noneMatch(existedCustomer -> existedCustomer.getLogin().equals(customer.getLogin()));
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }
}
