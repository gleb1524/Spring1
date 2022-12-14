package ru.karaban.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    @ManyToMany(mappedBy = "products")
    private List<User> users;
}
