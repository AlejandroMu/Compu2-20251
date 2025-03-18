package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dtos.StockInDto;
import com.example.demo.model.Buys;
import com.example.demo.services.interfaces.StockService;

@SpringBootTest
public class StockServiceTest {
    
    @Autowired
    private StockService stockService;

    @Test
    public void testRegisterStockIn() {
        List<StockInDto> stockInDtos = new ArrayList<>();
        Buys buys = new Buys();
    }
}
