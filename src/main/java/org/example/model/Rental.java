package org.example.model;

import org.example.model.Movie;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Rental implements Serializable, HasId {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String id;
    private final Movie movie;
    private Customer customer;
    private List<Movie> movies;
    private boolean approved;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented()  {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAmount() {
        return movie.getAmount(daysRented);
    }

    public int getBonus() {
        return movie.getBonus(daysRented);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String getId() {
        return id;
    }
}
