package org.example.view.impl;

import org.example.model.Customer;
import org.example.service.CustomerService;
import org.example.service.CustomerServiceImpl;
import org.example.view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomersMenuForAdmin implements Menu {
    private final CustomerService customerService = new CustomerServiceImpl();
    private final String[] items = new String[]{"1. Customers list", "0. Exit"};
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void show() {
        showItems(items);

        //noinspection InfiniteLoopStatement
        while (true) {
            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                System.out.println("Incorrect input");
                scanner.nextLine();
                show();
            }
            scanner.nextLine();
            switch (choice) {
                case 1 -> showAllCustomers();
                case 0 -> exit();
            }
        }
    }
    private void showAllCustomers() {
        List<Customer> customerList = customerService.getAll().stream()
                .filter(x -> x.getRole() == Customer.CustomerRole.CUSTOMER)
                 .collect(Collectors.toList());
        if (customerList.size() == 0) {
            System.out.println("---No customers---");
        } else {
            System.out.println("Customers list:");
            customerList.forEach(x -> System.out.println("\t" + x));
        }
        show();
    }

    @Override
    public void exit() {
        new AdminMainMenu().show();
    }
}
