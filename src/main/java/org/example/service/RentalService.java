package org.example.service;

import org.example.model.Movie;
import org.example.model.Rental;

import java.util.List;

public interface RentalService {

    void addMovieToRental(Movie movie, int amount);

    void approve(Rental rental);

    void refuse(Rental rental);

    List<Rental> getRentals();

    Rental getRentalByCustomer();

    List<Rental> getAllNotApprovedRentals();

    Rental getRentalById(String id);
}
