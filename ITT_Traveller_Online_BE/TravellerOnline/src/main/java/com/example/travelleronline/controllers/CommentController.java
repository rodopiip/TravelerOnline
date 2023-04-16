package com.example.travelleronline.controllers;

import com.example.travelleronline.model.entities.Comment;
import com.example.travelleronline.service.CommentService;
import com.example.travelleronline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfPost(@PathVariable("id") int id, HttpSession s) {
        UserController.isLogged(s);
        int userId=UserController.getId(s);
        //todo:  : if i make it like this, it will be a feature to see the comments only to those with reg.
        //of post with ID List<Comment> comments = commentService.findAll();
        List<Comment> comments = null;
        return ResponseEntity.ok(comments);
    }

    /*
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        //todo: logged in
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }
    */
    /* comment a user?
    @GetMapping("/users/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfUser() {
        //todo: logged in, loggedId
        //of current user List<Comment> comments = commentService.findAll();
        List<Comment> comments = null;
        return ResponseEntity.ok(comments);
    }
    */


    @GetMapping("/post/{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfComment(@PathVariable("id") int id) {
        //todo: postId
        //of post with ID List<Comment> comments = commentService.findAll();
        List<Comment> comments = null;
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") int id) {
        // specific comment || maybe make it all comments of a comment
        Comment comment = commentService.findById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment comment) {
        //todo: logged in
        Comment savedComment = commentService.save(comment);
        return savedComment;
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Integer id, @RequestBody Comment comment) {
        //todo: check if you're logged in
        Comment updatedComment = commentService.update(id, comment);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id) {
        //todo: Check if the owner is the one deleting
        //todo: can only delte if you are logged in

        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
