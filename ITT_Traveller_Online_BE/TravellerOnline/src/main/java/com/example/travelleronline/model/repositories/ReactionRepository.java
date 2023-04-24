package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
    public long countAllByPostId(int postId);

    @Query("SELECT r FROM Reaction r WHERE r.user.id = :userId AND r.comment.id = :commentId")
    Optional<Reaction> getByUserAndComment(int userId, int commentId);
    @Query("SELECT r FROM Reaction r WHERE r.user.id = :userId AND r.post.id = :postId")
    Optional<Reaction> getByUserAndPost(int userId,int postId);

}
