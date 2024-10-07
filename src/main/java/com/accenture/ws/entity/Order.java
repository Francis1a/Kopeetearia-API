package com.accenture.ws.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderName;
    private double price;
    private boolean isDiscounted;
    private static final double discountPercentage = 5.0;

    public Order() {}

    public Order(String orderName, double price, boolean isDiscounted) {
        this.orderName = orderName;
        this.price = price;
        this.isDiscounted = isDiscounted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }

    public double getDiscountedPercentage() {
        return discountPercentage;
    }
}
