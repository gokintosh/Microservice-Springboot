package com.gokul.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class InventoryResponseDto {
    private String skuCode;
    private Integer quantity;
}
