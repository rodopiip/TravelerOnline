package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.comment.CommentDTO;
import com.example.travelleronline.model.DTOs.comment.ContentDTO;
import com.example.travelleronline.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment Controller", description = "Comment endpoints")
@RestController
public class CommentController extends AbstractController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{id}/comments")
    public ContentDTO replyToPost(@PathVariable("id") int postId, @RequestBody ContentDTO contentData, HttpSession session) {
        return commentService.replyToPost(contentData,postId, getLoggedId(session));
    }
    @PostMapping("/comments/{id}/comments")
    public ContentDTO replyToComment(@PathVariable("id") int commentId, @RequestBody ContentDTO contentData, HttpSession session) {
        return commentService.replyToComment(contentData,commentId, getLoggedId(session));
    }
    @DeleteMapping("/comments/{id}")
    public ContentDTO deleteCommentById(HttpSession session, @PathVariable("id") int commentId) {
        return commentService.deleteById(commentId,getLoggedId(session));
    }
    @GetMapping("/comments/{id}")
    public CommentDTO getCommentById(@PathVariable("id") int id) {
        return commentService.getCommentWithChildComments(id);
    }
    @GetMapping("/posts/{postId}/comments")
    public Page<CommentDTO> getAllCommentsOfPost(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @PathVariable("postId") int postId, HttpSession s) {
        return commentService.getAllPostComments(pageNumber,postId);
    }
    @GetMapping("/comments")
    public Page<CommentDTO> getAllCommentsOfLoggedUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                       HttpSession session) {
        return commentService.getAllCommentOfUser(pageNumber,getLoggedId(session));
    }
    @GetMapping("/users/{userId}/comments")
    public Page<CommentDTO> getAllCommentsOfUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @PathVariable("userId") int userId) {
        return commentService.getAllCommentOfUser(pageNumber,userId);
    }
    @PostMapping("/comments/{commentId}/edit")
    public ContentDTO editComment(@PathVariable("commentId") int commentId,@RequestBody ContentDTO contentData,HttpSession s){
        return commentService.edit(commentId,contentData,getLoggedId(s));
    }
    @GetMapping("/test")
    public Page<CommentDTO> pageTest (@RequestParam(name = "page", defaultValue = "0") int pageNumber, HttpSession s){
        return commentService.pageTest(pageNumber, getLoggedId(s));
    }
}
