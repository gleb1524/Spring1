package ru.karaban.persist;

import java.util.HashMap;

public class Product {
    private long id;
    private String title;
    private int cost;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
