package SectionA.Question3;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    public Product(int id, String name, String category, double price) { this.id = id;
        this.name = name; this.category = category; this.price = price;
    }
    public String toString() { return name + " " + price;
    }
// Getter for each attribute


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
