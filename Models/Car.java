package Models;

import Interfaces.Product;

public class Car implements Product {
    private String id;
    private String name;
    private double price;

    public Car(int id, String name, double price) {
        this.id = "C-" + id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Price: " + price;
    }
}
