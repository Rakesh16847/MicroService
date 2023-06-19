package com.Microservice_Project.ProductService.service;

import com.Microservice_Project.ProductService.model.ProductRequest;
import com.Microservice_Project.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
