package Models;

public class Book extends Products {
    private String author;

    public Book(int id, String name, String author) {
        super(id, name);
        this.author = author;
    }
    @Override
    public String toString() {
        return super.toString() + " | Author: " + author;
    }
}
