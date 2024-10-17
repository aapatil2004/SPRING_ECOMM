package com.aryan.Project.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private double amount;
    private String customerEmail;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> productInfo;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<OrderItem> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<OrderItem> productInfo) {
        this.productInfo = productInfo;
    }

    // public Object map(Object object) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'map'");
    // }
}
