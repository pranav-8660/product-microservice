package com.pranavv51.microservices.springcloud.productsmicroservice.model;

import jakarta.persistence.*;

@Entity(name="product_details")
public class ProductsEntity {

    @Id
    @Column(name="product_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="product_name")
    private String name;
    @Column(name="product_description")
    private String description;
    @Column(name="product_price")
    private double price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductsEntity(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductsEntity(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductsEntity(){

    }

}
