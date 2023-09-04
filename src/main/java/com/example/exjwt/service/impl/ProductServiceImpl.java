package com.example.exjwt.service.impl;

import com.example.exjwt.data.dto.ProductDTO;
import com.example.exjwt.data.dto.ProductResponseDTO;
import com.example.exjwt.data.entity.Product;
import com.example.exjwt.data.repository.ProductRepository;
import com.example.exjwt.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO getProduct(Long number) {
        LOGGER.info("[getProduct] input number : {}", number);
        Product product = productRepository.findById(number).get();

        LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(product.getNumber());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;

    }

    @Override
    public ProductResponseDTO saveProduct(ProductDTO productDTO) {
        LOGGER.info("[saveProduct] productDTO : {}", productDTO.toString());
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        Product savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] savedProduct : {}", savedProduct);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(product.getNumber());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO changeProductName(Long number, String name) {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changeProduct = productRepository.save(foundProduct);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(changeProduct.getNumber());
        productResponseDTO.setName(changeProduct.getName());
        productResponseDTO.setPrice(changeProduct.getPrice());
        productResponseDTO.setStock(changeProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
       productRepository.deleteById(number);
    }
}
