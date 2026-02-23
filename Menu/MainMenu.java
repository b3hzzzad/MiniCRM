package Menu;

import Console.Console;
import DataBase.DataBase;
import Interfaces.Product;
import Managers.CustomerManager;
import Managers.ProductManager;

public class MainMenu {
    ProductManager<Product> productManager = new ProductManager<>();
    CustomerManager customerManager = new CustomerManager();
    Console console = new Console();


    public MainMenu(Console console, ProductManager<Product> productManager, CustomerManager customerManager) {
        this.console = console;
        this.productManager = productManager;
        this.customerManager = customerManager;
    }

    public void start() {
        while (true) {
            System.out.println("====================================");
            System.out.println("      CUSTOMER & PRODUCT SYSTEM     ");
            System.out.println("====================================");
            System.out.println("1. Product Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Order Management");
            System.out.println("4. Load Data");
            System.out.println("5. Exit");
            System.out.println("====================================");
            var options = console.getInt("Choose: ");


            switch (options) {
                case 1 -> new ProductsMenu(console, productManager).start();

                case 2 -> new CustomerMenu(console, customerManager).start();

                case 3 -> new OrderMenu(console, customerManager, productManager).start();

                case 4 -> loadData(productManager, customerManager);

                case 5 -> {
                    return;
                }
            }
        }
    }

    public static void loadData(ProductManager<Product> productManager, CustomerManager customerManager) {

        try {
            productManager.setProducts(DataBase.loadProducts());
            customerManager.setCustomers(DataBase.loadCustomers());
            System.out.println("Data loaded successfully.");
        } catch (java.io.IOException e) {
            System.out.println("Could not load data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

    }
}
