package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exeptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.StockIn;
import com.example.demo.model.StockOut;
import com.example.demo.reposiories.ProductRepository;
import com.example.demo.reposiories.StockInRepository;
import com.example.demo.reposiories.StockOutRepository;
import com.example.demo.services.impl.StockServiceImp;

@ExtendWith(MockitoExtension.class)
public class StockServiceTestUnit {
    
    @InjectMocks
    private StockServiceImp stockService;

    @Mock
    private StockInRepository stockInRepository;
    @Mock
    private StockOutRepository stockOutRepository;
    @Mock
    private ProductRepository productRepository;

    @Test
    public void testRegisterStockIn() {
        Product product = new Product();
        product.setId(1);
        product.setName("Test");
        product.setPrice(200);

        List<StockIn> stockInDtos = List.of(
            new StockIn(1, 10, 100, product, null),
            new StockIn(2, 20, 200, product, null),
            new StockIn(3, 30, 300, product, null)
        );

        List<StockOut> stockOuts = List.of(
            new StockOut(1, 10, 100, product, null),
            new StockOut(2, 20, 200, product, null),
            new StockOut(3, 30, 300, product, null)

        );

        product.setStockIns(stockInDtos);
        product.setStockOuts(stockOuts);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        try {
            int respuesta= stockService.getStockQuantity(1);
            assertEquals(0, respuesta);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFail(){
        try {
            when(productRepository.findById(1)).thenReturn(Optional.empty());
            stockService.getStockQuantity(1);
            fail();
        } catch (ProductNotFoundException e) {
            assertTrue(true);
        }
    }
}
