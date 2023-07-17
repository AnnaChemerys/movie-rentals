package org.example.model;

import org.example.model.MovieType;

public class NewRelease implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getBonus(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
