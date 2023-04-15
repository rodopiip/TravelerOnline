package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.post.NewPostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController extends AbstractController{

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public PostInfoDTO addPost(@RequestBody NewPostDTO newPostDTO){
        return postService.addPost(newPostDTO);
    }
}
