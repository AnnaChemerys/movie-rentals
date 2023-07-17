package org.example.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Customer implements Serializable, HasId {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    private String login;
    private String password;
    private CustomerRole role;
    private List<Rental> rentals;

    public enum CustomerRole {CUSTOMER, ADMIN}

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }

    public Customer(String login, String password, CustomerRole role) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.role = role;
    }

    //private final String name;

    public Customer(String login, String password, CustomerRole role, List<Rental> rentals) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.role = role;
        this.rentals = rentals;
    }

//    public Customer(String login, List<Rental> rentals) {
//        this.login = login;
//        this.rentals = rentals;
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerRole getRole() {
        return role;
    }

    public void setRole(CustomerRole role) {
        this.role = role;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getLogin() + "\n");
        for (Rental each : rentals) {
            double thisAmount = each.getAmount();

            frequentRenterPoints += each.getBonus();
            //show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    @Override
    public String getId() {
        return id;
    }
}
