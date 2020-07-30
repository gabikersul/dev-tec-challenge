package com.gabikersul.ilegra.technicalchallenge.model;


public class Product {

    private String id;
    private Integer quantity;
    private Double price;

    public Product(String id, Integer quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getProductTotalPrice(){
        return this.quantity * this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
