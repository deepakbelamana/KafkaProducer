package com.appsdeveloperblog.ws.products.controller;

import com.appsdeveloperblog.ws.products.model.CreateProductModel;
import com.appsdeveloperblog.ws.products.model.ErrorMessage;
import com.appsdeveloperblog.ws.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/produts")
public class ProductController {

    ProductService productService;

    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductModel createProductModel){
        String productId= null;
        try {
            productId = productService.createProduct(createProductModel);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                     new ErrorMessage(new Date(),e.getMessage(),"/products")
             );
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
