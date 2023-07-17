package org.example.main;

import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.MovieTypeFactory;
import org.example.model.Rental;
import org.example.view.impl.LoginMenu;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        new LoginMenu().show();
//        MovieTypeFactory typeFactory = MovieTypeFactory.getInstance();
//        List<Rental> rentals = List.of(new Rental(new Movie("Rambo", typeFactory.create("Regular")), 1),
//                new Rental(new Movie("Lord of the Rings", typeFactory.create("NewRelease")), 4),
//                new Rental(new Movie("Harry Potter", typeFactory.create("Children")), 5));
//
//        Customer customer = new Customer("John Doe", rentals);
//        String statement = customer.statement();
//
//        System.out.println(statement);
    }
}