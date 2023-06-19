package com.Microservice_Project.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @Column(name= "ProductName")
    private String productName;
    @Column(name= "ProductPrice")
    private long price;
    @Column(name= "ProductQuantity")
    private long quantity;
}
