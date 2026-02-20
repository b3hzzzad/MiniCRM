package Menus;

import Console.Console;
import Exceptions.CustomerNotFoundException;
import Exceptions.DuplicatedException;
import Exceptions.InvalidInputException;
import Exceptions.ProductNotFoundException;
import Interfaces.ICustomerManager;
import Interfaces.IProductManager;
import Models.Customer;
import Models.Products;

public class OrderMenu {

    private final Console console;
    private final ICustomerManager customerManager;
    private final IProductManager productManager;

    public OrderMenu(Console console, ICustomerManager customerManager, IProductManager productManager) {
        this.console = console;
        this.customerManager = customerManager;
        this.productManager = productManager;
    }

    public void start() {
        while (true) {
            System.out.println("===== ORDER MANAGEMENT =====");
            System.out.println("1. Place Order");
            System.out.println("2. Show Customer Orders");
            System.out.println("3. Cancel Order");
            System.out.println("4. Back");

            try {
                int option = console.getInt("Choose an option: ");

                switch (option) {
                    case 1 -> placeOrder();
                    case 2 -> showOrders();
                    case 3 -> cancelOrder();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void placeOrder() {
        try {

            int customerId = console.getInt("Enter Customer ID: ");
            int productId = console.getInt("Enter Product ID: ");

            Customer customer = customerManager.getCustomer(customerId);
            Products product = productManager.getProduct(productId);

            customer.orderProduct(product);
            System.out.println("Order placed successfully.");
        } catch (CustomerNotFoundException | ProductNotFoundException |
                 DuplicatedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showOrders() {
        try {


            int customerId = console.getInt("Enter Customer ID: ");
            Customer customer = customerManager.getCustomer(customerId);

            var orders = customer.getOrders();

            if (orders.isEmpty()) {
                System.out.println("This customer has no orders.");
                return;
            }

            System.out.println("===== CUSTOMER ORDERS =====");
            orders.forEach(System.out::println);
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        try {

            int customerId = console.getInt("Enter Customer ID: ");
            int productId = console.getInt("Enter Product ID to cancel: ");

            Customer customer = customerManager.getCustomer(customerId);
            customer.deleteOrder(productId);

            System.out.println("Order canceled successfully.");
        } catch (ProductNotFoundException | CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}