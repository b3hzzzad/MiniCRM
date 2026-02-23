package Menu;

import Console.Console;
import Exceptions.CustomerNotFoundException;
import Exceptions.DuplicateIdException;
import Interfaces.Product;
import Managers.CustomerManager;
import Managers.ProductManager;
import Models.Customer;

public class CustomerMenu {
    CustomerManager customerManager = new CustomerManager();
    Console console = new Console();

    public CustomerMenu(Console console, CustomerManager customerManager) {
        this.console = console;
        this.customerManager = customerManager;
    }

    public void start() {
        while (true) {
            System.out.println("===== Customer MANAGEMENT =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Show All Customer");
            System.out.println("4. Search Customer by ID");
            System.out.println("5. Back");

            var options = console.getInt("Choose: ");

            switch (options) {
                case 1:
                    addCustomer(console, customerManager);
                    break;
                case 2:
                    deleteCustomer(console, customerManager);
                    break;

                case 3:
                    showAllCustomers(customerManager);
                    break;

                case 4:
                    searchCustomer(console, customerManager);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Input.");
            }
        }
    }

    public static void addCustomer(Console console, CustomerManager customerManager) {
        var id = console.getInt("Enter customer ID: ");
        var name = console.getString("Enter Customer name: ");

        Customer customer = new Customer(id, name);
        System.out.println("Customer added successfully.");

        try {
            customerManager.addCustomer(customer);
        } catch (DuplicateIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteCustomer(Console console, CustomerManager customerManager) {
        var id = console.getString("Enter Customer ID: ");
        try {
            customerManager.deleteCustomer(id);
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showAllCustomers(CustomerManager customerManager) {
        try {

            customerManager.showAllCustomers();
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void searchCustomer(Console console, CustomerManager customerManager) {
        var id = console.getString("Enter customer ID: ");

        try {

            var searchedCustomer = customerManager.searchCustomer(id);
            System.out.println(searchedCustomer.toString());

        } catch (CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
