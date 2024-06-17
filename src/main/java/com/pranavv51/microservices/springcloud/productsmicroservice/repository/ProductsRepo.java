package com.pranavv51.microservices.springcloud.productsmicroservice.repository;

import com.pranavv51.microservices.springcloud.productsmicroservice.model.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<ProductsEntity,Integer> {
    public ProductsEntity findByid(int id);
    public ProductsEntity findByname(String name);
    public ProductsEntity findByIdAndName(int id,String name);
}
