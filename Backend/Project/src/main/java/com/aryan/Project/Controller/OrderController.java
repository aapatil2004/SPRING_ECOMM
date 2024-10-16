package com.aryan.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aryan.Project.Service.OrderService;
import com.aryan.Project.Model.Orders;
import com.aryan.Project.Model.ProductInfo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Checkout endpoint to create a new order
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody Orders order) {
        try {
            // Validate order input
            if (order.getProductInfo() == null || order.getProductInfo().isEmpty()) {
                return ResponseEntity.badRequest().body("Product information must not be empty.");
            }

            // Set the order date and status
            order.setOrderDate(LocalDateTime.now());
            order.setStatus("PENDING"); // Ensure status is set

            // Set order reference for product info
            for (ProductInfo product : order.getProductInfo()) {
                product.setOrder(order); // Set the reference back to the order
            }

            // Log the order being processed
            System.out.println("Received order request: " + order);

            // Save the order using the service
            Orders savedOrder = orderService.saveOrder(order);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error while processing the order: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process order: " + e.getMessage());
        }
    }

    // Endpoint to get all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get an order by ID
    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        }
        return ResponseEntity.ok(order);
    }
}
