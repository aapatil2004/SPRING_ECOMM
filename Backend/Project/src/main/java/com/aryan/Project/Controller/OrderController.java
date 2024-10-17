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

    // @GetMapping("/orders/{id}")
    // public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
    // return service.getorderById(id)
    // .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
    // .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }

    @GetMapping
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

    @DeleteMapping("/order/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
