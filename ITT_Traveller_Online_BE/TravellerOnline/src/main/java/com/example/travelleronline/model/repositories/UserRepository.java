package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;

public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);
    User findById(int id);
    boolean existsByEmail(String email);
}