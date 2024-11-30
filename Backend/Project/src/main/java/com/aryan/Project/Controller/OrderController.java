package com.aryan.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aryan.Project.Model.Orders;
import com.aryan.Project.Service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/checkout")
    public ResponseEntity<Orders> placeOrder(@RequestBody Orders order) {
        try {
            Orders savedOrder = service.saveorder(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        try {
            List<Orders> orders = service.findAll();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{email}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerEmail(@PathVariable String email) {
        System.out.println("The current user is : " + email);
        try {
            List<Orders> orders = service.findOrdersByCustomerEmail(email);
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/success")
    public ResponseEntity<String> orderSuccess(@RequestBody Orders order) {
        try {
            // Process order success (e.g., send confirmation, update payment status)
            return new ResponseEntity<>("Order success for Order ID: " + order.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Order success processing failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New route for failure if something goes wrong
    @PostMapping("/failure")
    public ResponseEntity<String> orderFailure(@RequestBody Orders order) {
        try {
            // Process order failure (e.g., cancel the order, update status)
            return new ResponseEntity<>("Order failed for Order ID: " + order.getId(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Order failure processing failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
