package Interfaces;

import Exceptions.DuplicatedException;
import Exceptions.ProductNotFoundException;
import Models.Products;

import java.util.ArrayList;

public interface IProductManager {
    void addProduct(Products products) throws DuplicatedException;

    Products getProduct(int id) throws ProductNotFoundException;

    void showProducts() throws NullPointerException;

    void removeProduct(int id) throws ProductNotFoundException;

    ArrayList<Products> getAllProducts();

    void setAllProducts(ArrayList<Products> products);
}
