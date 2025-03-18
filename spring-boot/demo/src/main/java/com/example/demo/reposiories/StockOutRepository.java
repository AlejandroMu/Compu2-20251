package com.example.demo.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StockOut;

@Repository
public interface StockOutRepository extends JpaRepository<StockOut, Integer> {
    
}
