package com.killian.SpringBoot.ExamApp.models;

import java.util.Calendar;

import org.springframework.data.annotation.Transient;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// A POJO 
@Entity
@Table(name = "tblProduct")
public class Product {
    // this is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // validate = constraint
    // @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int productYear;
    private Double price;
    private String url;

    // calculated value = transient
    @Transient
    private int age;

    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - productYear;
    }

    public Product() {
    }

    public Product(Long id, String productName, int productYear, Double price, String url) {
        this.id = id;
        this.productName = productName;
        this.productYear = productYear;
        this.price = price;
        this.url = url;
    }

    public Product(String productName, int productYear, Double price, String url) {
        this.productName = productName;
        this.productYear = productYear;
        this.price = price;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", productName=" + productName + ", year=" + productYear + ", price=" + price
                + ", url="
                + url + "]";
    }

    @Override
    public boolean equals(Object o) {
        return false;
        // if (this == o)
        //     return true;
        // else if (o == null || o.getClass() != this.getClass())
        //     return false;
        // Product oProduct = (Product) o;
        // return (productName.equals(oProduct.getProductName()) &&
        //         price.equals(oProduct.getPrice()) &&
        //         productYear == oProduct.getProductYear() &&
        //         url.equals(oProduct.getUrl()));
    }
}
