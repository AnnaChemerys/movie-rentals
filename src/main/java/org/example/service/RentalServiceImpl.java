package org.example.service;

import org.example.dao.MovieDao;
import org.example.dao.RentalDao;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.util.CurrentCustomer;

import java.util.ArrayList;
import java.util.List;

public class RentalServiceImpl implements RentalService{

    private final RentalDao rentalDao = new RentalDao();
    private final MovieDao movieDao = new MovieDao();
    private final MovieService movieService = new MovieServiceImpl();

    @Override
    public void addMovieToRental(Movie movie, int amount) {

        movieDao.update(movie);
    }

    private boolean isValidMovieForOrder(Movie movie, int quantity) {
        Movie tempMovie = movieDao.getById(movie.getId());
        if (tempMovie.getQuantityInStock() < quantity) {
            System.out.println("Product amount is not enough.");
            return false;
        }
        return true;
    }

    @Override
    public void approve(Rental rental) {
        if (!rental.isApproved()) {
            rental.setApproved(true);
            rentalDao.update(rental);
        } else {
            System.out.println("This rental is already approved");
        }
    }

    @Override
    public void refuse(Rental rental) {
        approve(rental);
        List<Movie> movies = new ArrayList<>();
       //WIP
    }

    @Override
    public List<Rental> getRentals() {
        return rentalDao.getAll();
    }

    @Override
    public Rental getRentalByCustomer() {
        return rentalDao.getRentalByCustomer(CurrentCustomer.customer);
    }

    @Override
    public List<Rental> getAllNotApprovedRentals() {
        List<Rental> notApprovedOrders = new ArrayList<>();
        for (Rental rental : rentalDao.getAll()) {
            if (!rental.isApproved()) {
                notApprovedOrders.add(rental);
            }
        }
        return notApprovedOrders;
    }

    public Rental getRentalById(String id) {
        return rentalDao.getById(id);
    }
}
