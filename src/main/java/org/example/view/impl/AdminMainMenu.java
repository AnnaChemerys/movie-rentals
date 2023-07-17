package org.example.view.impl;

import org.example.view.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMainMenu implements Menu {
    private final String[] items = {"1. Customers menu", "2. Rentals menu", "3. Movies menu", "0. Exit"};

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
                case 1 -> new CustomersMenuForAdmin().show();
                case 2 -> new RentalsMenu().show();
                case 3 -> new MovieMenu().show();
                case 0 -> exit();
            }
        }
    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }


}
