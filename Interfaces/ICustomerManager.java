package Interfaces;

import Exceptions.CustomerNotFoundException;
import Exceptions.DuplicatedException;
import Models.Customer;

import java.util.ArrayList;

public interface ICustomerManager {
    void addCustomer(Customer customer) throws DuplicatedException;

    Customer getCustomer(int id) throws CustomerNotFoundException;

    void showCustomers() throws CustomerNotFoundException;

    void removeCustomer(int id) throws CustomerNotFoundException;

    ArrayList<Customer> getCustomers();

    void setCustomers(ArrayList<Customer> customers);
     void showCustomersOrder(int id) throws CustomerNotFoundException;
}
