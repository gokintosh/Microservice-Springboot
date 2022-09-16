package com.gokul.productservice.service;


import com.gokul.productservice.dto.ProductRequest;
import com.gokul.productservice.dto.ProductResponse;
import com.gokul.productservice.model.Product;
import com.gokul.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product= Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
                        .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("product {} is saved",product.getId());

    }

    public List<ProductResponse>getAllProducts(){
        List<Product>listOfProducts=productRepository.findAll();

        return listOfProducts.stream().map(product -> mapToProductresponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductresponse(Product product) {
        return ProductResponse.builder().id(product.getId()).description(product.getDescription())
                .name(product.getName()).price(product.getPrice()).build();
    }
}
