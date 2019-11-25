package com.example.a511savetextfile_getexternalfilesdir;

public class Product {

    String name;
    String description;
    int quantity;
    int price;
    int image;

    public Product(String name, String description, int quantity, int price, int image) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Product(int price, int quantity, String name, int image) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
    }

    public Product(int price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
