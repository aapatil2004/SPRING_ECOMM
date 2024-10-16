package com.aryan.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aryan.Project.Model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}
