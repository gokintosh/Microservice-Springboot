package com.gokul.inventoryservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class InventoryResponseDto {

    private String skuCode;
    private Integer quantity;
}
