package DataBase;

import Models.Customer;
import Models.Products;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Database {

    private static final Gson gson = new Gson();

    public static void saveProducts(ArrayList<Products> products) {
        try (FileWriter writer = new FileWriter("products.json")) {
            gson.toJson(products, writer);
            System.out.println("Products saved.");
        } catch (Exception e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    public static ArrayList<Products> loadProducts() {
        try (FileReader reader = new FileReader("products.json")) {
            return gson.fromJson(reader, new TypeToken<ArrayList<Products>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveCustomers(ArrayList<Customer> customers) {
        try (FileWriter writer = new FileWriter("customers.json")) {
            gson.toJson(customers, writer);
            System.out.println("Customers saved.");
        } catch (Exception e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    public static ArrayList<Customer> loadCustomers() {
        try (FileReader reader = new FileReader("customers.json")) {
            return gson.fromJson(reader, new TypeToken<ArrayList<Customer>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}