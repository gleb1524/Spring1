package ru.karaban.persist;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class Product {

    private Long id;
    @NotBlank(message = "Can`t be empty.")
    private String title;
    @Min(1)
    private Long cost;
    @Min(0)
    private Long amount;
    @Min(0)
    private Long discount;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Past(message = "The date added cannot exceed the current date.")
    private Date dateAdded;

    public Product(String title, long cost) {
        this.title = title;
        this.cost = cost;
    }
}
