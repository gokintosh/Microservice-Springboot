package com.gokul.orderservice.service;

import com.gokul.orderservice.dto.OrderLineItemsDto;
import com.gokul.orderservice.dto.OrderRequest;
import com.gokul.orderservice.model.Order;
import com.gokul.orderservice.model.OrderLineItem;
import com.gokul.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem>orderLineItemList=orderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToOrderLineItem).collect(Collectors.toList());

        order.setOrderLineItems(orderLineItemList);
        orderRepository.save(order);

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
