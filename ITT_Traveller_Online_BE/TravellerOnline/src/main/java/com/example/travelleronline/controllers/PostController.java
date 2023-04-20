package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.post.CreatePostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@RequestMapping("localhost:3333")//question: is this necessary?
public class PostController extends AbstractController{

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public PostInfoDTO getPostById(@PathVariable("id") int id){
        return postService.getPostById(id);//todo service
    }
    @GetMapping("/users/{user-id}/posts")
    public List<PostInfoDTO> getUserPosts(HttpSession session){
        return postService.getUserPosts(getLoggedId(session));//todo service
    }
    @GetMapping("/posts")
    public List<PostInfoDTO> getPosts(){//newsfeed
        //todo Spring validation for criteria?
        //todo sort
        return postService.getPosts();
    }
    //VERSION 1
    public void/*PostInfoDTO*/ addPost(@RequestBody CreatePostDTO postInfoWithoutOwnerDTO,
                                       HttpSession s,
                                       @RequestBody List<MultipartFile> images,
                                       @RequestBody MultipartFile video){
        //get user id from session todo
        //postService.addPost(postInfoWithoutOwnerDTO, getLoggedId(s));//question: are media files needed?
        return;
    }
    @PostMapping(value = "/posts")
    //VERSION 2
    public PostInfoDTO addPost(){}
    @Transactional
    @PostMapping("/posts/{postId}")
    public PostInfoDTO updatePost(@PathVariable("postId") int postId, @RequestBody PostInfoDTO postInfoDTO, HttpSession s){

        return postService.updatePost(postId, postInfoDTO, getLoggedId(s));//todo service
    }

    //delete post - localhost:3333/posts/{postId}
    @Transactional//
    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable("postId") int postId, HttpSession s){
        int userId = getLoggedId(s);
        return postService.deletePost(postId, userId);
    }
}
