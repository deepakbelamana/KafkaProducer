package com.appsdeveloperblog.ws.products.controller;

import com.appsdeveloperblog.ws.products.model.CreateProductModel;
import com.appsdeveloperblog.ws.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produts")
public class ProductController {

    ProductService productService;

    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody CreateProductModel createProductModel){
        String productId=productService.createProduct(createProductModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
