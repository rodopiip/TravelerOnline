package com.example.travelleronline.service;

import com.example.travelleronline.model.entities.Comment;
import com.example.travelleronline.model.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(Integer id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        //todo: MAYBE WHEN GETTING A SPECIFIC COMMENT GET ALL SUB COMMENTS
        return commentOptional.orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment update(Integer id, Comment comment) {
        Comment existingComment = findById(id);
        existingComment.setContent(comment.getContent());
        existingComment.setPostId(comment.getPostId());
        existingComment.setSuperCommentId(comment.getSuperCommentId());
        existingComment.setRating(comment.getRating());
        return commentRepository.save(existingComment);
    }

    public void deleteById(Integer id) {
        Comment comment = findById(id);
        commentRepository.delete(comment);
    }
}
