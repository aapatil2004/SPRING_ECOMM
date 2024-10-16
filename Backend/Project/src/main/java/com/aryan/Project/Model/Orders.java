package com.aryan.Project.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // To allow saving of related
                                                                                    // product info
    private List<ProductInfo> productInfo = new ArrayList<>(); // Initialize to prevent NullPointerException

    private double amount; // Change to double for better precision with currency
    private String currency;
    private String customerEmail;
    private String status; // e.g., PENDING, COMPLETED, FAILED
    private LocalDateTime orderDate;

    // Constructors
    public Orders() {
        this.orderDate = LocalDateTime.now(); // Set order date automatically
    }

    public Orders(double amount, String currency, String customerEmail, String status, List<ProductInfo> productInfo) {
        this.amount = amount;
        this.currency = currency;
        this.customerEmail = customerEmail;
        this.status = status;
        this.productInfo = productInfo != null ? productInfo : new ArrayList<>();
        this.orderDate = LocalDateTime.now(); // Set order date automatically
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo != null ? productInfo : new ArrayList<>();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    // Override toString for better debugging
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", productInfo=" + productInfo +
                '}';
    }
}
