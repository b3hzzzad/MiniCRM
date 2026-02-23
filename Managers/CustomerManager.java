package Managers;

import DataBase.DataBase;
import Exceptions.CustomerNotFoundException;
import Exceptions.DuplicateIdException;
import Models.Customer;

import java.util.ArrayList;
import java.util.Objects;

public class CustomerManager {
    private ArrayList<Customer> arrayList = new ArrayList<>();

    public void addCustomer(Customer inputCustomer) throws DuplicateIdException {

        var condition = arrayList.stream()
                .anyMatch(c -> Objects.equals(c.getId(), inputCustomer.getId()));
        if (condition) throw new DuplicateIdException("Customer with this ID already exist.");

        arrayList.add(inputCustomer);

        DataBase.saveCustomers(arrayList);
    }

    public void deleteCustomer(String id) throws CustomerNotFoundException {
        var condition = arrayList.removeIf(customer -> Objects.equals(customer.getId(), id));
        if (!condition) throw new CustomerNotFoundException("Customer with this ID does not exist.");

        DataBase.saveCustomers(arrayList);
    }

    public Customer searchCustomer(String id) throws CustomerNotFoundException {
        var obj = arrayList.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with this ID does not exist."));

        return obj;

    }

    public void showAllCustomers() throws CustomerNotFoundException {
        if (arrayList.isEmpty()) throw new CustomerNotFoundException("No customers available.");
        arrayList.forEach(System.out::println);
    }

    //load data
    public void setCustomers(ArrayList<Customer> customers) {
        this.arrayList = customers;
    }

}
