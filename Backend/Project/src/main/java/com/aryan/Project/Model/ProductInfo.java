package com.aryan.Project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price; // Change to double for price
    private int quantity; // Assuming quantity is an integer

    @ManyToOne // Establish the many-to-one relationship
    @JoinColumn(name = "order_id", nullable = false) // Ensure order_id is not nullable
    private Orders order; // Reference back to the Orders entity

    // Constructors
    public ProductInfo() {
    }

    public ProductInfo(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
