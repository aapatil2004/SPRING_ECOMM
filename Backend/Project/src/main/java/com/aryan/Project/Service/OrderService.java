package com.aryan.Project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.Project.Model.Orders;
import com.aryan.Project.Repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;

    public Orders saveorder(Orders order) {
        return repo.save(order);
    }

    // Find an order by ID
    public Orders getorderById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Retrieve all orders
    public List<Orders> findAll() {
        return repo.findAll();
    }

    // Delete an order by ID
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
