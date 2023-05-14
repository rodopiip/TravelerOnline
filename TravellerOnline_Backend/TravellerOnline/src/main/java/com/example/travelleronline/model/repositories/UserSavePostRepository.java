package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.UserSavePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSavePostRepository extends JpaRepository<UserSavePost,Integer> {
    Optional<UserSavePost> findByUserIdAndPostId(int userId, int postId);
    @Query("SELECT usp FROM UserSavePost usp WHERE usp.user.id = :userId")
    List<UserSavePost> findAllByUserId(@Param("userId") Long userId);

}
