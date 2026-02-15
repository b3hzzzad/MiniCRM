package Models;

public class Products {
    final private String name;
    final private int id;

    public Products(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name;
    }
}
