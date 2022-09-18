package com.gokul.inventoryservice.service;

import com.gokul.inventoryservice.dto.InventoryResponseDto;
import com.gokul.inventoryservice.model.Inventory;
import com.gokul.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> skuCode){
        List<Inventory> inventoryList= inventoryRepository.findBySkuCodeIn(skuCode);

        return inventoryList.stream().map(this::mapToInventoryResponseDto).collect(Collectors.toList());
    }

    private InventoryResponseDto mapToInventoryResponseDto(Inventory inventory) {
        return InventoryResponseDto.builder().quantity(inventory.getQuantity()).skuCode(inventory.getSkuCode()).build();
    }
}
