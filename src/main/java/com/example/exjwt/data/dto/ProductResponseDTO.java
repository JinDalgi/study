package com.example.exjwt.data.dto;

import lombok.Getter;

@Getter
public class ProductResponseDTO {

    private Long number;
    private String name;

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private int price;
    private int stock;

    public ProductResponseDTO() {}

    public ProductResponseDTO(Long number, String name, int price, int stock) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static ProductResponseDTOBuilder builder() {
        return new ProductResponseDTOBuilder();
    }


    public static class ProductResponseDTOBuilder {
        private Long number;
        private String name;
        private int price;
        private int stock;

        ProductResponseDTOBuilder() {
        }

        public ProductResponseDTOBuilder number(Long number) {
            this.number = number;
            return this;
        }

        public ProductResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductResponseDTOBuilder price(int price) {
            this.price = price;
            return this;
        }

        public ProductResponseDTOBuilder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public ProductResponseDTO build() {
            return new ProductResponseDTO(number, name, price, stock);
        }

        @Override
        public String toString() {
            return "ProductResponseDTO.ProductResponseDTOBuilder(number = " + number +
                    ", name = " + name + ", price = " + price + ", stock = " + stock + ")";
        }
    }
}
