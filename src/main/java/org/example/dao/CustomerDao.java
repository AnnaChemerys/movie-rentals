package org.example.dao;

import org.example.model.Customer;

import java.util.List;

public class CustomerDao extends AbstractDao<Customer> {

    @Override
    protected String getFileName() {
        return FileNames.CUSTOMERS.getFileName();
    }

    @Override
    public void update(Customer customer) {
        List<Customer> tempList = items;
        for (Customer customerTemp : tempList) {
            if (customerTemp.equals(customer)) {
                customerTemp.setRole(customer.getRole());
                customerTemp.setPassword(customer.getPassword());
            }
        }
        fileOperation.writeIntoFile(tempList, filename);
        items = tempList;
    }

    public Customer getByLogin(String login) {
        List<Customer> tempList = items;
        for (Customer customerTemp : tempList) {
            if (customerTemp.getLogin().equals(login)) {
                return customerTemp;
            }
        }
        return null;
    }
}
