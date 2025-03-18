package com.example.demo.dtos;

import lombok.Data;

@Data
public class StockInDto {
    private int productId;
    private int quantity;
    private double price;
}
