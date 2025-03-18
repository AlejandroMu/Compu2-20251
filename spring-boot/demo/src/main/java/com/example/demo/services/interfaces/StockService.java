package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.dtos.StockInDto;
import com.example.demo.model.Buys;
import com.example.demo.model.Sale;
import com.example.demo.model.StockIn;
import com.example.demo.model.StockOut;

public interface StockService {

    public List<StockIn> registerStockIn(List<StockInDto> stockInDtos, Buys buys);

    public List<StockOut> registerStockOut(List<StockInDto> stockInDtos, Sale sale);
    
    public Integer getStockQuantity(int productId);
}
