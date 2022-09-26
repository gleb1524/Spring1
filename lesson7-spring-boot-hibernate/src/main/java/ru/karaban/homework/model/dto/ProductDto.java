package ru.karaban.homework.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter

@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;



    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @NotBlank(message = "can not be empty")
    private String title;
    @Min(0)
    private BigDecimal cost;

    public void setId(Long id) {
        this.id = id;
    }
}
