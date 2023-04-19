package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.comment.ContentDTO;
import com.example.travelleronline.model.entities.Comment;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentController extends AbstractController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public Comment commentPost(@PathVariable("id") int postId, @RequestBody ContentDTO contentData, HttpSession session) {
        return commentService.saveByPost(contentData,postId, getLoggedId(session));
    }
    @PostMapping("/comments/{id}/comments")
    public Comment commentComment(@PathVariable("id") int commentId, @RequestBody ContentDTO contentData, HttpSession session) {
        return commentService.saveByComment(contentData,commentId, getLoggedId(session));
    }
    @Transactional
    @DeleteMapping("/comments/{id}")
    public void deleteUserBySessionUserId(HttpSession session,@PathVariable("id") int commentId) {
        getLoggedId(session);
        commentService.deleteById(commentId);
    }
    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable("id") int id) {
        // specific comment || maybe make it all comments of a comment
        Comment comment = commentService.findById(id);
        return comment;
    }
    @GetMapping("/comments/{id}/comments")
    public List<Comment> getAllCommentsOfComment(@PathVariable("id") int id) {
        return commentService.getAllSubComments(id);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getAllCommentsOfPost(@PathVariable("postId") int postId, HttpSession s) {
        try {
            return commentService.getAllPostComments(postId);
        }catch(Exception e) {
            throw new NotFoundException("Post does not exist");
        }
    }
    @GetMapping("/comments")
    public List<Comment> getAllCommentsOfUser(HttpSession session) {
        return commentService.getAllCommentOfUser(getLoggedId(session));
    }
}
