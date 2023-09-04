package com.example.exjwt.data.dto;

import lombok.Getter;

@Getter
public class ChangeProductNameDTO {

    private Long number;
    private String name;

    public ChangeProductNameDTO(Long number, String name) {
        this.number = number;
        this.name = name;
    }

    public ChangeProductNameDTO(){}

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
