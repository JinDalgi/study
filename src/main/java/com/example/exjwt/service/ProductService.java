package com.example.exjwt.service;

import com.example.exjwt.data.dto.ProductDTO;
import com.example.exjwt.data.dto.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO getProduct(Long number);
    ProductResponseDTO saveProduct(ProductDTO productDTO);

    ProductResponseDTO changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
