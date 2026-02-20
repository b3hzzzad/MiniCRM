package Models;

import Exceptions.DuplicatedException;
import Exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;

    private final ArrayList<Products> arrayList = new ArrayList<>();

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Products> getOrders() {
        return new ArrayList<>(arrayList);
    }
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name;
    }

    public void orderProduct(Products theOrderProduct) throws DuplicatedException {
        for (Products p : arrayList) {
            if (theOrderProduct.getId() == p.getId()) {
                throw new DuplicatedException("Order already exists.");
            }
        }
        arrayList.add(theOrderProduct);
    }

    public void deleteOrder(int id) throws ProductNotFoundException {
        Products toRemove = null;

        for (Products p : arrayList) {
            if (p.getId() == id) {
                toRemove = p;
                break;
            }
        }

        if (toRemove == null) {
            throw new ProductNotFoundException("Order with ID " + id + " not found for this customer.");
        }

        arrayList.remove(toRemove);
        System.out.println("Order " + id + " has been cancelled.");
    }
}
