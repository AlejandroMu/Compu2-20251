package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dtos.StockInDto;
import com.example.demo.model.Buys;
import com.example.demo.model.StockIn;
import com.example.demo.reposiories.StockInRepository;
import com.example.demo.services.interfaces.StockService;

@SpringBootTest
public class StockServiceTest {
    
    @Autowired
    private StockService stockService;

    @Autowired
    private StockInRepository stockInRepository;

    @Test
    public void testRegisterStockIn() {
        List<StockInDto> stockInDtos = List.of(
            new StockInDto(1, 10, 100),
            new StockInDto(2, 20, 200),
            new StockInDto(3, 30, 300)
        );

        Buys buys = new Buys();
        buys.setDate(new Timestamp(System.currentTimeMillis()));
        buys.setProvider("Test");
        buys.setTicketCode("ABC123");
        buys.setUser(null);

        stockService.registerStockIn(stockInDtos, buys);
        List<StockIn> stockIns = stockInRepository.findAll();
        assertEquals(3, stockIns.size());
        assertEquals(buys, stockIns);

    }
}
