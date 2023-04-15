package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
