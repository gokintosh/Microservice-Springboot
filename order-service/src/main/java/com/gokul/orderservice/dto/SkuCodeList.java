package com.gokul.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class SkuCodeList {
    private List<String>skuCodes;
}
