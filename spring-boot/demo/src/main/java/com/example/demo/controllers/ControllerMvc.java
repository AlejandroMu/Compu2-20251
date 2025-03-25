package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Product;
import com.example.demo.services.interfaces.ProductService;

@Controller
@RequestMapping("/products")
public class ControllerMvc {

    @Autowired
    private ProductService productService;
    
    @GetMapping
    public String productsList(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products/products.html";
    }

    @GetMapping("/new")
    public String getProductForm(){
        return "products/productForm.html";
    }

    @PostMapping("/new")
    public String createProduct(Product product){
        System.out.println("\n-------name: " + product.getName() + " price: " + product.getPrice()+"\n");
        productService.addProduct(product);
        return "redirect:/products";
    }



}