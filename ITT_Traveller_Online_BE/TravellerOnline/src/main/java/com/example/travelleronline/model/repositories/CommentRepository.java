package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByPostId(Integer postId);
    List<Comment> findAllByUserId(Integer userId);
    Optional<Comment> findById(Integer id);
    Set<Comment> findAllByParentCommentId(Integer id);
}
