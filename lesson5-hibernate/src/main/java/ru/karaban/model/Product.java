package ru.karaban.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Long cost;

    @Column(nullable = false)
    private Long amount;

    @Column
    private Long discount;

    @Column(nullable = false)
    private LocalDate dateAdded;

    public Product(String title, Long cost, Long amount, Long discount, LocalDate dateAdded) {


        this.title = title;
        this.cost = cost;
        this.amount = amount;
        this.discount = discount;
        this.dateAdded = dateAdded;
    }
}
