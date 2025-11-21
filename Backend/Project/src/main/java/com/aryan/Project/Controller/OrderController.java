package com.aryan.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aryan.Project.Model.Orders;
import com.aryan.Project.Service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
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
    public RedirectView orderSuccess(HttpServletRequest request) {
        try {
            String status = request.getParameter("status");
            // String txnid = request.getParameter("txnid");
            // String hash = request.getParameter("hash");

            if ("success".equals(status))
                return new RedirectView("http://localhost:5173/order/success");
            else
                return new RedirectView("http://localhost:5173/order/failure");
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("http://localhost:5173/order/failure");
        }
    }

    // New route for failure if something goes wrong
    @PostMapping("/failure")
    public RedirectView orderFailure(HttpServletRequest request) {
        try {
            String txnid = request.getParameter("txnid");
            return new RedirectView("http://localhost:5173/order/failure");
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("http://localhost:5173/order/failure");
        }
    }
}
