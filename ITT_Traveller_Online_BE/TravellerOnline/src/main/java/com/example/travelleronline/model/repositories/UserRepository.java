package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    Optional<User> findByVerificationCode(String code);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM User u WHERE " +
            "(u.firstName) LIKE (CONCAT('%', :firstName, '%')) OR " +
            "(u.lastName) LIKE (CONCAT('%', :lastName, '%')) OR " +
            "(u.email) LIKE (CONCAT('%', :email, '%')) OR " +
            "(u.phoneNumber) LIKE (CONCAT('%', :phoneNumber, '%'))")
    List<User> searchUsers(@Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("email") String email,
                           @Param("phoneNumber") String phoneNumber);
}