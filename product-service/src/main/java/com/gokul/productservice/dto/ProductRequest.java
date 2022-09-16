package com.gokul.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "product")
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
}
