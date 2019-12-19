package com.example.a511savetextfile_getexternalfilesdir;

public class Product {

    String name;
    int price;
    int quantity;
    int image;
    boolean isChecked;

    public Product(String name, int price, int quantity, int image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getImage() {
        return image;
    }

    public boolean getChecked() {
        return isChecked;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
