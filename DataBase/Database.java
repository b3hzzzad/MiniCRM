package DataBase;

import Interfaces.Product;
import Models.Book;
import Models.Customer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataBase {

    private static final Gson gson = new Gson();


    // -------------------- Products --------------------
    public static ArrayList<Product> loadProducts() throws IOException {
        return load("products.json", new TypeToken<ArrayList<Book>>(){}.getType());
    }

    public static void saveBooks(ArrayList<Product> books) {
        save("products.json", books);
    }

    // -------------------- CUSTOMERS (unchanged) --------------------
    public static ArrayList<Customer> loadCustomers() throws IOException{
        return load("customers.json", new TypeToken<ArrayList<Customer>>(){}.getType());
    }

    public static void saveCustomers(ArrayList<Customer> customers) {
        save("customers.json", customers);
    }

    // -------------------- GENERIC SAVE/LOAD --------------------
    private static <T> void save(String fileName, T data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
            System.out.println(fileName + " saved.");
        } catch (Exception e) {
            System.out.println("Error saving " + fileName + ": " + e.getMessage());
        }
    }

    private static <T> T load(String fileName, java.lang.reflect.Type type)throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            return (T) new ArrayList<>();
        }
    }

}
