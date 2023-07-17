package org.example.dao;

import org.example.model.Customer;
import org.example.model.Rental;

import java.util.List;

public class RentalDao extends AbstractDao<Rental> {
    @Override
    protected String getFileName() {
        return FileNames.RENTALS.getFileName();
    }

    @Override
    public void update(Rental rental) {
        List<Rental> tempList = items;
        for (Rental rentalTemp : tempList) {
            if (rentalTemp.getCustomer() != null && rentalTemp.getCustomer().equals(rental.getCustomer())) {
                rentalTemp.setMovies(rental.getMovies());
                rentalTemp.setApproved(rental.isApproved());
            }
        }
        fileOperation.writeIntoFile(tempList, filename);
        items = tempList;
    }

    public Rental getRentalByCustomer(Customer customer) {
        for (Rental rentalTemp : items) {
            if (rentalTemp.getCustomer() != null && rentalTemp.getCustomer().equals(customer) && !rentalTemp.isApproved()) {
                return rentalTemp;
            }
        }
        return null;
    }
}
