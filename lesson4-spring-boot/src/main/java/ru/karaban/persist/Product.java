package ru.karaban.persist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@Entity()
@Table(name = "PRODUCTS")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;
    @Min(1)
    @Column(nullable = false)
    private Long cost;

    @Min(0)
    @Column()
    private Long amount;

    @Min(0)
    @Column()
    private Long discount;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Past(message = "The date added cannot exceed the current date.")
    @Column()
    private Date dateAdded;

    public Product(String title, long cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, Long cost, Long amount, Long discount, Date dateAdded) {
        this.title = title;
        this.cost = cost;
        this.amount = amount;
        this.discount = discount;
        this.dateAdded = dateAdded;
    }
}
