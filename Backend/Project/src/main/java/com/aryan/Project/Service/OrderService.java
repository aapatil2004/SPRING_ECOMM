package com.aryan.Project.Service;

import com.aryan.Project.Model.Orders;
import com.aryan.Project.Repository.OrderRepo; // Assume you have an OrderRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    // Save an order
    public Orders saveOrder(Orders order) {
        // Validate order data if necessary
        if (order.getProductInfo() == null || order.getProductInfo().isEmpty()) {
            throw new IllegalArgumentException("Product information must not be empty.");
        }

        // Ensure status is set before saving
        order.setStatus("COMPLETED"); // You can also set this based on your requirements
        order.setOrderDate(LocalDateTime.now()); // Set the order date to now
        return orderRepository.save(order);
    }

    // Get all orders
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    public Orders getOrderById(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id)); // Throw
    }
}
