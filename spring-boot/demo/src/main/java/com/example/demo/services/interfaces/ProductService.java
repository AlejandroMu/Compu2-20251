package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
    
    public List<Product> getProducts();
    public void addProduct(Product product);

}
