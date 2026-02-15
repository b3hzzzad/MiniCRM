package Models;

public class Car extends Products {
    private double price;

    public Car(int id, String name, double price) {
        super(id, name);
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString() + " | Price: " + price;
    }
}
