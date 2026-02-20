package Managers;

import DataBase.Database;
import Exceptions.CustomerNotFoundException;
import Exceptions.DuplicatedException;
import Interfaces.ICustomerManager;
import Models.Customer;

import java.util.ArrayList;

public class CustomerManager implements ICustomerManager {

    private ArrayList<Customer> arrayList;

    public CustomerManager() {
        this.arrayList = Database.loadCustomers();
    }


    @Override
    public void addCustomer(Customer customer) throws DuplicatedException {
        for (Customer c : arrayList) {
            if (c.getId() == customer.getId()) {
                throw new DuplicatedException("Duplicate ID.");
            }
        }
        arrayList.add(customer);
        Database.saveCustomers(arrayList);
    }

    public ArrayList<Customer> getCustomers() {
        return new ArrayList<>(arrayList);
    }

    @Override
    public void setCustomers(ArrayList<Customer> customers) {
        this.arrayList = customers;
        Database.saveCustomers(arrayList);
    }

    @Override
    public void showCustomers() throws CustomerNotFoundException {
        if (arrayList.isEmpty()) {
            throw new NullPointerException("No customers found.");
        }
        for (Customer c : arrayList) System.out.println(c.toString());
    }

    @Override
    public Customer getCustomer(int id) throws CustomerNotFoundException {
        for (Customer c : arrayList) {
            if (id == c.getId()) return c;
        }
        throw new CustomerNotFoundException("Models.Customer with this ID not found.");
    }

    @Override
    public void removeCustomer(int id) throws CustomerNotFoundException {
        Customer c = getCustomer(id);
        arrayList.remove(c);
        Database.saveCustomers(arrayList);
    }


    @Override
    public void showCustomersOrder(int id) throws CustomerNotFoundException {
        Customer customer = getCustomer(id);
        System.out.println(customer);
    }
}

