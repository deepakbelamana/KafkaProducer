package com.appsdeveloperblog.ws.products.service;


import com.appsdeveloperblog.ws.products.model.CreateProductModel;

public interface ProductService {
    String createProduct(CreateProductModel createProductModel) throws Exception;
}
