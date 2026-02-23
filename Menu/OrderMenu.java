package Menu;

import Console.Console;
import Exceptions.CustomerNotFoundException;
import Exceptions.ProductNotFoundException;
import Interfaces.Product;
import Managers.CustomerManager;
import Managers.ProductManager;

public class OrderMenu {
    ProductManager<Product> productManager = new ProductManager<>();
    CustomerManager customerManager = new CustomerManager();
    Console console = new Console();

    public OrderMenu(Console console, CustomerManager customerManager, ProductManager<Product> productManager) {
        this.console = console;
        this.productManager = productManager;
        this.customerManager = customerManager;
    }

    public void start() {
        while (true) {
            System.out.println("===== Order MANAGEMENT =====");
            System.out.println("1. Add Order");
            System.out.println("2. Delete Order");
            System.out.println("3. Back");

            var options = console.getInt("Choose: ");
            switch (options) {
                case 1:
                    addOrder(console, customerManager, productManager);
                    break;

                case 2:
                    deleteOrder(console, customerManager, productManager);
                    break;

                case 3:
                    return;
                default:
                    System.out.println("Invalid Input.");
            }
        }
    }

    public static void addOrder(Console console, CustomerManager customerManager, ProductManager<Product> productManager) {
        var customerId = console.getString("Enter Customer ID: ");
        var productId = console.getString("Enter Product ID: ");

        try {
            var customer = customerManager.searchCustomer(customerId);
            var item = productManager.searchProduct(productId);

            customer.addOrder(item);

            System.out.println("Order added.");


        } catch (CustomerNotFoundException | ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteOrder(Console console, CustomerManager customerManager, ProductManager<Product> productManager) {
        var customerId = console.getString("Enter Customer ID: ");
        var productId = console.getString("Enter Product ID: ");

        try {
            var customer = customerManager.searchCustomer(customerId);
            customer.deleteOrder(productId);
            System.out.println("Order deleted.");


        } catch (CustomerNotFoundException | ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
