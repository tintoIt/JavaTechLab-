package org.example;

public class Product {
    private int id;
    private String name;
    private double price;
    private String color;

    public Product() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.color = "";
    }

    public Product(String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public Product(int id, String name, double price, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}