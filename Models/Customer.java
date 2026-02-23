package Models;

import Exceptions.ProductNotFoundException;
import Interfaces.Product;

import java.util.ArrayList;
import java.util.Objects;

public class Customer {
    private String id;
    private String name;

    private ArrayList<Product> arrayList = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder ordersText = new StringBuilder();

        if (arrayList.isEmpty()) {
            ordersText.append("No orders");
        } else {
            for (Product Product : arrayList) {
                ordersText.append("\n   - ").append(Product.toString());
            }
        }

        return "ID: " + id + " | Name: " + name + "\nOrders: " + ordersText;
    }

    public String getId() {
        return id;
    }

    public Customer(int id, String name) {
        this.id = "C-" + id;
        this.name = name;

    }

    public void addOrder(Product product) {
        arrayList.add(product);
    }

    public void deleteOrder(String id) throws ProductNotFoundException {
        var condition = arrayList.removeIf(b -> Objects.equals(b.getId(), id));
        if (!condition) throw new ProductNotFoundException("Book with this ID not found.");
    }
}
