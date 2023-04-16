package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.comment.ContentDTO;
import com.example.travelleronline.model.entities.Comment;
import com.example.travelleronline.model.exceptions.BadSaveToDBException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.service.CommentService;
import com.example.travelleronline.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public Comment commentPost(@PathVariable("id") int postId, @RequestBody ContentDTO contentData, HttpSession s) {
        SessionService.isLogged(s);
        int userId=UserController.getId(s);
        return commentService.saveByPost(userId,contentData,postId);
    }
    @PostMapping("/comments/{id}/comments")
    public Comment commentCommend(@PathVariable("id") int commentId, @RequestBody ContentDTO contentData, HttpSession s) {
        SessionService.isLogged(s);
        int userId=UserController.getId(s);
        return commentService.saveByComment(userId,contentData,commentId);
    }
    @DeleteMapping("/comments/{id}")
    public void deleteUserBySessionUserId(HttpSession s,@PathVariable("id") int commentId) {
        SessionService.isLogged(s);
        System.out.println("Entering:"+commentId);
        commentService.deleteById(commentId,s);
        return ;
    }


    // from here

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
    public List<Comment> getAllCommentsOfUser(HttpSession s) {
        SessionService.isLogged(s);
        return commentService.getAllCommentOfUser(UserController.getId(s));
    }



    /* comment a user?
    @GetMapping("/users/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfUser() {
        //todo: logged in, loggedId
        //of current user List<Comment> comments = commentService.findAll();
        List<Comment> comments = null;
        return ResponseEntity.ok(comments);
    }
    */
}
