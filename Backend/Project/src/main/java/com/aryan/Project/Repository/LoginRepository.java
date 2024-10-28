package com.aryan.Project.Repository;

import com.aryan.Project.Model.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);

    Login findByEmail(String email); // Added method to find by email
}
