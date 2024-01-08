package com.storeTechnology.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeTechnology.demo.model.Products;
import com.storeTechnology.demo.model.ResponseObject;
import com.storeTechnology.demo.reponsitories.ProductResponsitory;


@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

    @Autowired
    private ProductResponsitory productResponsitory;

    @GetMapping("")
    List<Products> fildAll(){
        return productResponsitory.findAll();
    }

    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) { // reponseEntity dung de tao 1 danh sach va su dung method
        Optional<Products> foundProduct = productResponsitory.findById(id);
        return foundProduct.isPresent() ? // isPresent de tim doi tuong
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Query product successfully", foundProduct) // chuan form
                // You can replace "OK" with your defined "error code"
        ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find product with id = " + id, "")
        );
    }

    // insert data
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Products newproduct) { // @requesbody la kieu du lieu truyen vao - body
        // kiem tra xem san pham moi co bi trung ten khong
        List<Products> founProducts = productResponsitory.findByProductName(newproduct.getProductName().trim());
        if(founProducts.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "In database has name", "") // chuan form
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Product has inserted successfully", productResponsitory.save(newproduct)) // chuan form
        );
    }

    // update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Products newProducts, @PathVariable Long id){
        // neu tim thay id thi sua con khong thi sua id
        Products updateProduct = productResponsitory.findById(id) 
                .map(product -> {
                    product.setProductName(newProducts.getProductName());
                    product.setYear(newProducts.getYear());
                    product.setPrices(newProducts.getPrices());
                    product.setUrl(newProducts.getUrl());
                    return productResponsitory.save(product);
                }).orElseGet(() -> {
                    newProducts.setId(id);
                    return productResponsitory.save(newProducts);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Update product successfully", updateProduct));
    }

    // Delete product
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) { // reponseEntity dung de tao 1 danh sach va su dung method
        boolean exits = productResponsitory.existsById(id);
        if (exits){
            productResponsitory.deleteById(id); // neu tìm thấy thì xoá theo id
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Delete Product successfully", ""));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Cannot find product to delete", "")   
        );
    }


}
