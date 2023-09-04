package com.example.exjwt.data.repository;

import com.example.exjwt.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
