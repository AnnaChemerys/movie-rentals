package org.example.view.impl;

import org.example.service.RentalService;
import org.example.service.RentalServiceImpl;
import org.example.view.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMainMenu implements Menu {
    private final RentalService rentalService = new RentalServiceImpl();
    private final String[] items = {"1. Product menu", "2. My orders", "0. Exit"};

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
                case 1 -> new MovieMenu().show();
                case 2 -> myRentals();
                case 0 -> exit();
            }

        }
    }

    private void myRentals() {
        System.out.println(rentalService.getRentals() == null ? "-----No orders-----" : rentalService.getRentalByCustomer());
        show();
    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }
}
