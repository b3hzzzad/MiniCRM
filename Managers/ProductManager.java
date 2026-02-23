package Managers;

import Exceptions.DuplicateIdException;
import Exceptions.ProductNotFoundException;
import Interfaces.Product;
import DataBase.DataBase;

import java.util.ArrayList;
import java.util.Objects;

public class ProductManager<T extends Product> {

    private ArrayList<Product> arrayList = new ArrayList<>();

    public void addProduct(Product inputProduct) throws DuplicateIdException {
        var condition = arrayList.stream()
                .anyMatch(b -> Objects.equals(b.getId(), inputProduct.getId()));
        if (condition) throw new DuplicateIdException("Product already exist.");
        arrayList.add(inputProduct);

        DataBase.saveBooks(arrayList);

    }

    public void deleteProduct(int inputId) throws ProductNotFoundException {

        var condition = arrayList.removeIf(b -> Integer.parseInt(b.getId()) == inputId);
        if (!condition) throw new ProductNotFoundException("Product with this ID does not exist.");

        DataBase.saveBooks(arrayList);
    }

    public void showAllProducts() throws ProductNotFoundException {
        if (arrayList.isEmpty()) throw new ProductNotFoundException("Products are empty.");

        arrayList.forEach(System.out::println);
    }

    public Product searchProduct(String id) throws ProductNotFoundException {

        var obj = arrayList.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst()
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with this ID does not exist."));

        return obj;

    }

    //load data
    public void setProducts(ArrayList<Product> Product) {
        this.arrayList = Product;
    }
}
