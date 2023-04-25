package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    Optional<User> findByVerificationCode(String code);
    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "INSERT INTO users_subscribe_to_users (subscribed_to_id, subscriber_id) VALUES (?1, ?2)", nativeQuery = true)
    void addSubscription(int subscribedToId, int subscriberId);
    @Modifying
    @Query(value = "DELETE FROM users_subscribe_to_users WHERE subscribed_to_id = ?1 AND subscriber_id = ?2", nativeQuery = true)
    void deleteSubscription(int subscribedToId, int subscriberId);
    @Query(value = "SELECT COUNT(*) FROM travellerdb.users_subscribe_to_users WHERE subscribed_to_id = ?1 AND subscriber_id = ?2", nativeQuery = true)
    int subscriptionExist(int subscribedToId, int subscriberId);

    @Query(value = "SELECT subscribed_to_id FROM travellerdb.users_subscribe_to_users WHERE subscriber_id = ?1", nativeQuery = true)
    List<Integer> findSubscribedToIdsBySubscriberId(int subscriberId);

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