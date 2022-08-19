package ru.karaban.persist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private Long cost;

    public Product(String title, long cost) {
        this.title = title;
        this.cost = cost;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public long getCost() {
//        return cost;
//    }
//
//    public void setCost(long cost) {
//        this.cost = cost;
//    }
}
