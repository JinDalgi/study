package com.example.exjwt.data.dto;

import lombok.Getter;

@Getter
public class ProductDTO {
    private String name;
    private int price;
    private int stock;

    public ProductDTO(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductDTO() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
