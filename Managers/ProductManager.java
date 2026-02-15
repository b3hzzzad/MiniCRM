package Managers;

import DataBase.Database;
import Exceptions.DuplicatedException;
import Exceptions.ProductNotFoundException;
import Interfaces.IProductManager;
import Models.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements IProductManager {

    private ArrayList<Products> arrayList;

    public ProductManager() {
        this.arrayList = Database.loadProducts();
    }

    public ArrayList<Products> getAllProducts() {
        return arrayList;
    }

    @Override
    public void setAllProducts(ArrayList<Products> products) {
        this.arrayList = products;
        Database.saveProducts(arrayList);
    }


    @Override
    public void addProduct(Products product) throws DuplicatedException {
        for (Products p : arrayList) {
            if (p.getId() == product.getId()) {
                throw new DuplicatedException("Duplicate ID.");
            }
        }
        arrayList.add(product);
        Database.saveProducts(arrayList);
    }



    @Override
    public Products getProduct(int id) throws ProductNotFoundException {
        for (Products p : arrayList) {
            if (id == p.getId()) return p;
        }
        throw new ProductNotFoundException("Product with this ID not found.");
    }

    @Override
    public void showProducts() {
        if (arrayList.isEmpty()) {
            throw new NullPointerException("No products available.");
        }
        for (Products p : arrayList) System.out.println(p.toString());
    }

    @Override
    public void removeProduct(int id) throws ProductNotFoundException {
        Products p = getProduct(id);
        arrayList.remove(p);
        Database.saveProducts(arrayList);
    }

}
