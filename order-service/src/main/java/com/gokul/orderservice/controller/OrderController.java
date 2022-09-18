package com.gokul.orderservice.controller;

import com.gokul.orderservice.dto.OrderRequest;
import com.gokul.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) throws IllegalAccessException {

        orderService.placeOrder(orderRequest);
        return "order placed successfully!!";
    }
}
