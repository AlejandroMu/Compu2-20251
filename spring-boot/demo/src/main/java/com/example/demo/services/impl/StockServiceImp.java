package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.StockInDto;
import com.example.demo.model.Buys;
import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.model.StockIn;
import com.example.demo.model.StockOut;
import com.example.demo.reposiories.ProductRepository;
import com.example.demo.reposiories.StockInRepository;
import com.example.demo.reposiories.StockOutRepository;
import com.example.demo.services.interfaces.StockService;

@Service
public class StockServiceImp implements StockService{

    private StockInRepository stockInRepository;
    private StockOutRepository stockOutRepository;
    private ProductRepository productRepository;

    @Override
    public List<StockIn> registerStockIn(List<StockInDto> stockInDtos, Buys buys) {
        List<StockIn> stockIns = new ArrayList<>();

        for (StockInDto stockInDto : stockInDtos) {
            Product product = productRepository.findById(stockInDto.getProductId()).get();
            StockIn stockIn = new StockIn();
            stockIn.setBuys(buys);
            stockIn.setPrice(stockInDto.getPrice());
            stockIn.setProduct(product);
            stockIn.setQuantity(stockInDto.getQuantity());
            stockIns.add(stockIn);
        }

        return stockInRepository.saveAll(stockIns);
    }

    public List<StockOut> registerStockOut(List<StockInDto> stockInDtos, Sale sale) {
        List<StockOut> stockOuts = new ArrayList<>();
    
        for (StockInDto stockInDto : stockInDtos) {
            Product product = productRepository.findById(stockInDto.getProductId()).get();
            StockOut stockOut = new StockOut();
            stockOut.setSale(sale);
            stockOut.setPrice(stockInDto.getPrice());
            stockOut.setProduct(product);
            stockOut.setQuantity(stockInDto.getQuantity());
            stockOuts.add(stockOut);
        }

        return stockOutRepository.saveAll(stockOuts);
    }

    @Override
    public Integer getStockQuantity(int productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
