package com.example.demo.exeptions;

public class ProductNotFoundException extends Exception {

    private int idProducto;

    public ProductNotFoundException(String message, int idProducto) {
        super(message);
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
