package com.gokul.orderservice.service;

import com.gokul.orderservice.dto.InventoryResponseDto;
import com.gokul.orderservice.dto.OrderLineItemsDto;
import com.gokul.orderservice.dto.OrderRequest;
import com.gokul.orderservice.dto.SkuCodeList;
import com.gokul.orderservice.model.Order;
import com.gokul.orderservice.model.OrderLineItem;
import com.gokul.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;


    public void placeOrder(OrderRequest orderRequest) throws IllegalAccessException {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem>orderLineItemList=orderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToOrderLineItem).collect(Collectors.toList());

        order.setOrderLineItems(orderLineItemList);

//        we need to collect the skuCode from the order list
        List<String>skuCodes=order.getOrderLineItems().stream()
                        .map(OrderLineItem::getSkuCode)
                                .collect(Collectors.toList());

        InventoryResponseDto[] inventoryResponseDto=webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder ->
                        uriBuilder.queryParam("skuCodes",skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponseDto[].class)
                                        .block();



        boolean allProductsInStock= Arrays.stream(inventoryResponseDto)
                .allMatch(InventoryResponseDto::getQuantity);
        //todo create a new function in inventory to get product not empty

        if(allProductsInStock){
            orderRepository.save(order);
        }else {
            throw new IllegalAccessException("Some quantities are out of stock , please try again!");
        }



    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItem orderLineItem=new OrderLineItem();
        orderLineItem.setId(orderLineItemsDto.getId());
        orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItem.setPrice(orderLineItemsDto.getPrice());
        orderLineItem.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItem;
    }

}
