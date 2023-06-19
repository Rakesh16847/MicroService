package com.Microservice_Project.ProductService.service;

import com.Microservice_Project.ProductService.entity.Product;
import com.Microservice_Project.ProductService.exception.ProductServiceCustomException;
import com.Microservice_Project.ProductService.model.ProductRequest;
import com.Microservice_Project.ProductService.model.ProductResponse;
import com.Microservice_Project.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("It Started");
        Product product
                = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Data saved successfully");
        return product.getProductId();


    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("It will get started" , productId);
        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceCustomException("Product not found", "NoTFound")
                );
        ProductResponse productResponse
                = new ProductResponse();
        copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity for id:",quantity,productId);

        Product product=
                productRepository.findById(productId)
                        .orElseThrow(() -> new ProductServiceCustomException(
                                "Product given not found","PRODUCT_NOT_FOUND"
                        ));
        if (product.getQuantity() < quantity){
            throw new ProductServiceCustomException(
                    "Product dosnot have sufficent quantity", "INSUFFICENT_QUANTITY"
            );
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product quantity updated Successfully:");
    }
}
