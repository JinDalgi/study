package com.example.exjwt.controller;

import com.example.exjwt.data.dto.ChangeProductNameDTO;
import com.example.exjwt.data.dto.ProductDTO;
import com.example.exjwt.data.dto.ProductResponseDTO;
import com.example.exjwt.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDTO> getProduct(Long number) {
        ProductResponseDTO productResponseDTO = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_Token", required = true,
            dataType = "String", paramType = "header")
    })
    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductResponseDTO productResponseDTO = productService.saveProduct(productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDTO> changeProductName(@RequestBody ChangeProductNameDTO changeProductNameDTO) throws Exception {
        ProductResponseDTO productResponseDTO = productService.changeProductName(
                changeProductNameDTO.getNumber(),
                changeProductNameDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
