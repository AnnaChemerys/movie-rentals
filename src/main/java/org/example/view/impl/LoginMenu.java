package org.example.view.impl;

import org.example.model.Customer;
import org.example.service.CustomerService;
import org.example.service.CustomerServiceImpl;
import org.example.util.CurrentCustomer;
import org.example.view.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginMenu implements Menu {
    private final CustomerService customerService = new CustomerServiceImpl();
    private final String[] items = {"1.Login", "2.Customer registration", "3.Administrator registration", "0. Exit"};
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
            switch (choice) {
                case 1 -> loginSubMenu();
                case 2 -> registerUser();
                case 3 -> registerAdmin();
                case 0 -> exit();
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void loginSubMenu() {
        System.out.println("Input login:");
        scanner.nextLine();
        String login = scanner.nextLine();

        System.out.println("Input password:");
        String password = scanner.nextLine();
        Customer customer = customerService.login(login, password);
        if (customer != null) {
            CurrentCustomer.customer = customer;
            System.out.println("Successfully authorization");

                if (customer.getRole().equals(Customer.CustomerRole.CUSTOMER)) {
                    new CustomerMainMenu().show();
                } else {
                    new AdminMainMenu().show();
                }
        } else {
            System.out.println("Wrong username/password");
            show();
        }
    }

    private void registerUser() {
        System.out.println("Input login:");
        scanner.nextLine();
        String login = scanner.nextLine();

        System.out.println("Input password:");
        String password = scanner.nextLine();

        Customer customer = new Customer(login, password, Customer.CustomerRole.CUSTOMER);
        customerService.register(customer);
        System.out.println("Customer was registered successfully ");
        show();
    }

    private void registerAdmin() {
        System.out.println("Input login:");
        scanner.nextLine();
        String login = scanner.nextLine();

        System.out.println("Input password:");
        String password = scanner.nextLine();

        Customer customer = new Customer(login, password, Customer.CustomerRole.ADMIN);
        customerService.register(customer);

        System.out.println("Admin was  registered successfully");
        show();
    }
}
