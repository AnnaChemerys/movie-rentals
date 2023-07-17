package org.example.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Movie implements Serializable, HasId {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    private String title;
    private String director;
    private String shortDescription;
    private String actors;
    private MovieType priceCode;

    private int quantityInStock;

    public Movie(String title, MovieType priceCode) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.priceCode = priceCode;
    }

    public Movie(String id, String title, MovieType priceCode) {
        this.id = id;
        this.title = title;
        this.priceCode = priceCode;
    }

    public Movie(String id, String title, String director, String shortDescription, String actors, MovieType priceCode, int quantityInStock) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.shortDescription = shortDescription;
        this.actors = actors;
        this.priceCode = priceCode;
        this.quantityInStock = quantityInStock;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriceCode(MovieType priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public double getAmount(int daysRented) {
        return getPriceCode().getAmount(daysRented);
    }

    public int getBonus(int daysRented) {
        return getPriceCode().getBonus(daysRented);
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getMovieType() {
        return getPriceCode().getClass().getSimpleName().toLowerCase();
    }
}
