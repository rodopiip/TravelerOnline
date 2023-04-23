package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.comment.CommentDTO;
import com.example.travelleronline.model.DTOs.comment.ContentDTO;
import com.example.travelleronline.model.entities.Comment;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment Controller", description = "Comment endpoints")
@RestController
public class CommentController extends AbstractController {
    @Autowired
    private CommentService commentService;

    //create
    //todo : suggestion : : refactor : reply to post
    @PostMapping("/posts/{id}/comments")
    public ContentDTO commentPost(@PathVariable("id") int postId, @RequestBody ContentDTO contentData, HttpSession session) {
        return commentService.saveByPost(contentData,postId, getLoggedId(session));
    }
    //todo : suggestion : refactor: reply to comment
    @PostMapping("/comments/{id}/comments")
    public ContentDTO commentComment(@PathVariable("id") int commentId, @RequestBody ContentDTO contentData, HttpSession session) {
        return commentService.saveByComment(contentData,commentId, getLoggedId(session));
    }

    //delete
    //todo : suggestion : refactor: deleteByUserSessionId
    @DeleteMapping("/comments/{id}")
    public ContentDTO deleteUserBySessionUserId(HttpSession session,@PathVariable("id") int commentId) {
        return commentService.deleteById(commentId,getLoggedId(session));
    }

    //get
    @GetMapping("/comments/{id}")
    public CommentDTO getCommentById(@PathVariable("id") int id) {
        return commentService.getCommentWithChildComments(id);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getAllCommentsOfPost(@PathVariable("postId") int postId, HttpSession s) {
        return commentService.getAllPostComments(postId);
    }
    @GetMapping("/comments")
    public List<CommentDTO> getAllCommentsOfLoggedUser(HttpSession session) {
        return commentService.getAllCommentOfUser(getLoggedId(session));
    }
    @GetMapping("/users/{userId}/comments")
    public List<CommentDTO> getAllCommentsOfUser(@PathVariable("userId") int userId) {
        return commentService.getAllCommentOfUser(userId);
    }
    //edit
    @PostMapping("/comments/{commentId}/edit")
    public ContentDTO editComment(@PathVariable("commentId") int commentId,@RequestBody ContentDTO contentData,HttpSession s){
        return commentService.edit(commentId,contentData,getLoggedId(s));
    }

}
