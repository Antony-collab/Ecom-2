package com.example.Ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Ecom.model.Product;

public interface ProductRepo extends  JpaRepository<Product,Integer>{

}
