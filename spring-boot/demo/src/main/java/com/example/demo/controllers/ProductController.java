package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Product;
import com.example.demo.services.interfaces.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping
    public String productsList(Model model, Authentication auth){
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products/products.html";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getProductForm(){
        return "products/productForm.html";
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createProduct(Product product){
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable(name = "id") Integer idProduct, Model model){
        Product product = productService.getProduct(idProduct);
        model.addAttribute("product", product);
        return "products/productDetail.html";
    }



}