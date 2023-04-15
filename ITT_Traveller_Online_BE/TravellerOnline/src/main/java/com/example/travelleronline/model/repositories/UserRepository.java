package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}