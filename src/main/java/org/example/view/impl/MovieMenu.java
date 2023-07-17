package org.example.view.impl;

import org.example.model.Customer;
import org.example.model.Movie;
import org.example.service.MovieService;
import org.example.service.MovieServiceImpl;
import org.example.service.RentalService;
import org.example.service.RentalServiceImpl;
import org.example.util.CurrentCustomer;
import org.example.view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.model.Customer.CustomerRole.CUSTOMER;

public class MovieMenu implements Menu {
    private final RentalService rentalService = new RentalServiceImpl();
    private final MovieService movieService = new MovieServiceImpl();
    private final Customer.CustomerRole currentRole = CurrentCustomer.customer.getRole();

    private final String[] items = currentRole == CUSTOMER
            ? new String[]{"1. Movie list", "2. Search movie", "3. Add movie to rental", "4. Rental checkout", "0. Exit"}
            : new String[]{"1. Movie list", "2. Edit movie", "3. Add movie", "0. Exit"};

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
            switch (currentRole) {
                case CUSTOMER -> {
                    switch (choice) {
                        case 1 -> movieList();
                        case 2 -> new SearchMovieMenu().show();
                        case 3 -> addMovieToRental();
                        case 4 -> orderCheckout();
                        case 0 -> exit();
                    }
                }
                case ADMIN -> {
                    switch (choice) {
                        case 1 -> movieList();
                        case 2 -> editMovie();
                        case 3 -> addMovie();
                        case 0 -> exit();
                    }
                }
            }
        }
    }

    private void addMovieToRental() {
        System.out.println("Enter order ID: ");
        scanner.nextLine();
        String id = scanner.nextLine();

        Movie movieToAdd = movieService.getMovieById(id);

        if (movieToAdd != null) {
            System.out.println(movieToAdd);
            System.out.println("Enter quantity: ");
            int productAmount = scanner.nextInt();
            //WIP
        } else {
            System.out.println("Invalid ID");
        }
        show();
    }

    private void addMovie() {
        System.out.println("Enter product name: ");
        scanner.nextLine();
        String name = scanner.nextLine();


        System.out.println("Enter product amount: ");

        //WIP
        movieService.saveMovie();

        show();
    }

    private void editMovie() {
        System.out.println("Enter movie ID: ");
        scanner.nextLine();
        String productId = scanner.nextLine();

        Movie movieToAdd = movieService.getMovieById(productId);

        if (movieToAdd != null) {

           //WIP
        show();
    }

    private void orderCheckout() {
       //WIP
        show();
    }

    private void movieList() {
        if (movieService.getAllMovies().size() == 0) {
            System.out.println("----No products----");
        } else {
            int increase = 3;
            int page = 0;
            int[] pageIndex = pageIndex(movieService.getAllMovies(), increase, page);
            boolean exitz = false;
            while (!exitz) {
                System.out.println("Movie list:");
                movieService.getAllMovies().subList(pageIndex[1], pageIndex[2]).forEach(x -> System.out.println("\t" + x));
                if (pageIndex[0] == 0 && increase >= movieService.getAllMovies().size()) {
                    System.out.println("        /         / 3. Exit");
                } else if ((pageIndex[0] * increase) + increase >= movieService.getAllMovies().size()) {
                    System.out.println("1. Prev /         / 3. Exit");
                } else if (pageIndex[0] == 0 && increase < movieService.getAllMovies().size()) {
                    System.out.println("        / 2. Next / 3. Exit");
                } else {
                    System.out.println("1. Prev / 2. Next / 3. Exit");
                }

                int customerChoice = -1;
                try {
                    customerChoice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }

                switch (customerChoice) {
                    case 1 -> pageIndex = pageIndex(movieService.getAllMovies(), increase, --pageIndex[0]);
                    case 2 -> pageIndex = pageIndex(movieService.getAllMovies(), increase, ++pageIndex[0]);
                    case 3 -> exitz = true;
                }

            }
        }
        show();
    }

    public int[] pageIndex(List items, int increase, int page) {
        int pageOut;
        int start;
        int end;
        int[] index = new int[3];
        int maxPages = items.size() / increase;

        if (page < 0) {
            pageOut = 0;
            start = pageOut;
            end = increase;
        } else if (page >= maxPages) {
            pageOut = maxPages;
            start = pageOut * increase;
            end = items.size();
        } else {
            pageOut = page;
            start = page * increase;
            end = start + increase;
        }

        index[0] = pageOut;
        index[1] = start;
        index[2] = end;
        return index;
    }

    @Override
    public void exit() {
        if (CurrentCustomer.customer.getRole() == CUSTOMER) {
            new CustomerMainMenu().show();
        } else {
            new AdminMainMenu().show();
        }
    }
}
