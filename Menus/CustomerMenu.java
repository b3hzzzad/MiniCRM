package Menus;

import Console.Console;
import Exceptions.CustomerNotFoundException;
import Exceptions.DuplicatedException;
import Exceptions.InvalidInputException;
import Interfaces.ICustomerManager;
import Models.Customer;

public class CustomerMenu {

    private final Console console;
    private final ICustomerManager customerManager;

    public CustomerMenu(Console console, ICustomerManager customerManager) {
        this.console = console;
        this.customerManager = customerManager;
    }

    public void start() {
        while (true) {
            System.out.println("===== CUSTOMER MANAGEMENT =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Show All Customers");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");

            try {
                int option = console.getInt("Choose an option: ");

                switch (option) {
                    case 1 -> addCustomer();
                    case 2 -> customerManager.showCustomers();
                    case 3 -> searchCustomer();
                    case 4 -> deleteCustomer();
                    case 5 -> { return; }
                    default -> System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addCustomer() throws InvalidInputException, DuplicatedException {
        int id = console.getInt("Enter Customer ID: ");
        String name = console.getString("Enter Customer Name: ");

        customerManager.addCustomer(new Customer(id, name));
    }

    private void searchCustomer() throws InvalidInputException, CustomerNotFoundException {
        int id = console.getInt("Enter Customer ID: ");
        System.out.println(customerManager.getCustomer(id));
    }

    private void deleteCustomer() throws InvalidInputException, CustomerNotFoundException {
        int id = console.getInt("Enter Customer ID: ");
        customerManager.removeCustomer(id);
    }
}