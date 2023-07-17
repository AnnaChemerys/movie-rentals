package org.example.dao;

public enum FileNames {

    RENTALS("rentals.ser"), MOVIES("movies.ser"), CUSTOMERS("customers.ser");

    private final String fileName;

    FileNames(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
