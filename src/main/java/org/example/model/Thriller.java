package org.example.model;

public class Thriller implements MovieType{
    @Override
    public double getAmount(int daysRented) {
        return 0;
    }

    @Override
    public int getBonus(int daysRented) {
        return 0;
    }
}
