package Menu;

import Console.Console;
import Exceptions.DuplicateIdException;
import Exceptions.ProductNotFoundException;
import Interfaces.Product;
import Managers.CustomerManager;
import Managers.ProductManager;
import Models.Book;
import Models.Car;

public class ProductsMenu {
    ProductManager<Product> productManager = new ProductManager<>();
    Console console = new Console();

    public ProductsMenu(Console console, ProductManager<Product> productManager) {
        this.console = console;
        this.productManager = productManager;

    }

    public void start() {
        while (true) {
            System.out.println("===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Add Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Show All Products");
            System.out.println("6. Search Product by ID");
            System.out.println("7. Back");

            var options = console.getInt("Choose: ");

            switch (options) {
                case 1 -> addBook(console, productManager);
                case 2 -> deleteBook(console, productManager);
                case 3 -> addCar(console, productManager);
                case 4 -> deleteCar(console, productManager);
                case 5 -> showAllProducts(productManager);
                case 6 -> searchProduct(console, productManager);
                case 7 -> {
                    return;
                }

                default -> System.out.println("Invalid Input.");
            }
        }
    }

    public static void addBook(Console console, ProductManager<Product> productManager) {

        int id = console.getInt("Enter Book ID (number only): ");
        String name = console.getString("Enter Book Name: ");
        double price = console.getDouble("Enter Price: ");

        Book book = new Book(id, name, price);
        try {

            productManager.addProduct(book);
            System.out.println("Book added successfully.");
        } catch (DuplicateIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteBook(Console console, ProductManager<Product> productManager) {
        int id = console.getInt("Enter Book ID (number only): ");

        try {
            productManager.deleteProduct(id);
            System.out.println("Book deleted.");
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addCar(Console console, ProductManager<Product> productManager) {

        int id = console.getInt("Enter Car ID (number only): ");
        String name = console.getString("Enter Car Name: ");
        double price = console.getDouble("Enter Price: ");

        Car car = new Car(id, name, price);
        try {

            productManager.addProduct(car);
            System.out.println("Car added successfully.");
        } catch (DuplicateIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteCar(Console console, ProductManager<Product> productManager) {
        int id = console.getInt("Enter Car ID (number only): ");

        try {
            productManager.deleteProduct(id);
            System.out.println("Car deleted.");
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showAllProducts(ProductManager<Product> productManager) {
        try {

            productManager.showAllProducts();
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchProduct(Console console, ProductManager<Product> productManager) {

        String id = console.getString("Enter Product ID (With prefix): ").trim();
        try {

            var searchedItem = productManager.searchProduct(id);
            System.out.println(searchedItem.toString());
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
