package Menus;

import Console.Console;
import DataBase.Database;
import Exceptions.InvalidInputException;
import Interfaces.ICustomerManager;
import Interfaces.IProductManager;

public class MenuController {

    private final Console console;
    private final IProductManager productManager;
    private final ICustomerManager customerManager;

    public MenuController(Console console, IProductManager productManager, ICustomerManager customerManager) {
        this.console = console;
        this.productManager = productManager;
        this.customerManager = customerManager;
    }

    public void start() {
        while (true) {
            printMainMenu();

            try {
                int option = console.getInt("Enter option: ");

                switch (option) {
                    case 1 -> new ProductMenu(console, productManager).start();
                    case 2 -> new CustomerMenu(console, customerManager).start();
                    case 3 -> new OrderMenu(console, customerManager, productManager).start();
                    case 4 -> saveAllData();
                    case 5 -> loadAllData();
                    case 6 -> {
                        System.out.println("Exiting program...");
                        return;
                    }
                    default -> System.out.println("Invalid input.");
                }

            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println("====================================");
        System.out.println("      CUSTOMER & PRODUCT SYSTEM     ");
        System.out.println("====================================");
        System.out.println("1. Product Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Order Management");
        System.out.println("4. Save Data");
        System.out.println("5. Load Data");
        System.out.println("6. Exit");
        System.out.println("====================================");
    }
    private void saveAllData() {
        Database.saveProducts(productManager.getAllProducts());
        Database.saveCustomers(customerManager.getCustomers());
        System.out.println("All data saved successfully.");
    }

    private void loadAllData() {
        productManager.setAllProducts(Database.loadProducts());
        customerManager.setCustomers(Database.loadCustomers());
        System.out.println("All data loaded successfully.");
    }

}