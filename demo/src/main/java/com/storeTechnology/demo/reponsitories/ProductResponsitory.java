package com.storeTechnology.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storeTechnology.demo.model.Products;
import java.util.List;

// moi quan he giua server va database
@Repository
public interface ProductResponsitory extends JpaRepository<Products, Long>{
    List<Products> findByProductName(String productName); // tao 1 mang cac sp co ten
}
