package Menus;

import Console.Console;
import Exceptions.DuplicatedException;
import Exceptions.InvalidInputException;
import Exceptions.ProductNotFoundException;
import Interfaces.IProductManager;
import Models.Book;
import Models.Car;
import Models.Products;

public class ProductMenu {

    private final Console console;
    private final IProductManager productManager;

    public ProductMenu(Console console, IProductManager productManager) {
        this.console = console;
        this.productManager = productManager;
    }

    public void start() {
        while (true) {
            System.out.println("===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Add Product");
            System.out.println("2. Show All Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Delete Product");
            System.out.println("5. Back");

            try {
                int option = console.getInt("Choose an option: ");

                switch (option) {
                    case 1 -> addProductMenu();
                    case 2 -> productManager.showProducts();
                    case 3 -> searchProduct();
                    case 4 -> deleteProduct();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addProductMenu() {
        System.out.println("""
                ===== ADD PRODUCT =====
                1. Add Book
                2. Add Car
                3. Back""");
        try {


            int option = console.getInt("Choose: ");

            switch (option) {
                case 1 -> addBook();
                case 2 -> addCar();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid input.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addBook() {
        try {

            int id = console.getInt("Enter Book ID: ");
            String name = console.getString("Enter Book Name: ");
            String author = console.getString("Enter Author: ");

            productManager.addProduct(new Book(id, name, author));
            System.out.println("Book added successfully.");
        } catch (DuplicatedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addCar() {
        try {

            int id = console.getInt("Enter Car ID: ");
            String name = console.getString("Enter Car Name: ");
            double price = console.getDouble("Enter Price: ");

            productManager.addProduct(new Car(id, name, price));
            System.out.println("Car added successfully.");
        } catch (RuntimeException | DuplicatedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchProduct() {

        try {
            int id = console.getInt("Enter Product ID: ");

            Products product = productManager.getProduct(id);

            System.out.println("===== PRODUCT FOUND =====");
            System.out.println(product);
        } catch (RuntimeException | ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        try {


            int id = console.getInt("Enter Product ID: ");
            productManager.removeProduct(id);

            System.out.println("Product deleted successfully.");
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}