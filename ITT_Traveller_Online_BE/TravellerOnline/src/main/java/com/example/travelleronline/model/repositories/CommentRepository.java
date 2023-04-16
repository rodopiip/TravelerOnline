package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Comment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findBySuperCommentId(Integer superCommentId);
    List<Comment> findAllByPostId(Integer postId);
    List<Comment> findAllByUserId(Integer userId);
}
