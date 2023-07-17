package org.example.model;

public class Children implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }

    @Override
    public int getBonus(int daysRented) {
        return 1;
    }
}
