package com.Microservice_Project.ProductService.controller;

import com.Microservice_Project.ProductService.model.ProductRequest;
import com.Microservice_Project.ProductService.model.ProductResponse;
import com.Microservice_Project.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductCrontroller {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId= productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        ProductResponse productResponse
                = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{id}")
    public  ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,
                                                @RequestParam long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
