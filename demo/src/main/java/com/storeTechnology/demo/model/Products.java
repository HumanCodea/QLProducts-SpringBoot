package com.storeTechnology.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.Calendar;


@Entity
@Table(name = "product") // ten cua bang trong sql
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // you can use "sequence" - viet quy tac -- 
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String productName;
    private int Year1;
    private String prices;
    private String url;

    public Products(String productName, int Year1, String prices, String url) {
        this.productName = productName;
        this.Year1 = Year1;
        this.prices = prices;
        this.url = url;
    }
    public Products() {
    }

    // calculated field = transien, not exits in mysql
    @Transient
    private int age;
    public int getAgae(){
        return Calendar.getInstance().get(Calendar.YEAR) - Year1;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getYear() {
        return Year1;
    }
    public void setYear(int Year1) {
        this.Year1 = Year1;
    }
    public String getPrices() {
        return prices;
    }
    public void setPrices(String prices) {
        this.prices = prices;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return "Products [id=" + id + ", productName=" + productName + ", year=" + Year1 + ", prices=" + prices
                + ", url=" + url + "]";
    }

    
}
