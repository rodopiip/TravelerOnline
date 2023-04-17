package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.post.CreatePostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@RequestMapping("localhost:3333")//question: is this necessary?
public class PostController extends AbstractController{

    @Autowired
    private PostService postService;

    //get post by post_id - localhost:3333/posts/{id}
    @GetMapping("/posts/{id}")
    public PostInfoDTO getPostById(@PathVariable("id") int id){
        return postService.getPostById(id);//todo service
    }

    //get posts by user_id - localhost:3333/users/posts
    @GetMapping("/users/{user-id}/posts")
    public List<PostInfoDTO> getUserPosts(HttpSession session){
        return postService.getUserPosts(getLoggedId(session));//todo service
    }

    //get all posts - localhost:3333/posts
    @GetMapping("/posts")
    public List<PostInfoDTO> getPosts(){//newsfeed
        //question: criteria for getting posts?
        //todo sort
        return postService.getPosts();//todo service
    }

    public void/*PostInfoDTO*/ addPost(@RequestBody CreatePostDTO postInfoWithoutOwnerDTO,
                                       HttpSession s,
                                       @RequestBody List<MultipartFile> images,
                                       @RequestBody MultipartFile video){
        //get user id from session todo
        //postService.addPost(postInfoWithoutOwnerDTO, getLoggedId(s));//question: are media files needed?
        return;
    }

    //add video to post - localhost:3333/posts/{postId}/upload-video
    @PostMapping("/posts/{postId}/upload-video")
    public PostInfoDTO addPostVideo(@PathVariable("postId") int id){
        //Implement the logic to upload and store the video file, then update the video URL in the post.
        //return /* updated post DTO */;//todo
        return null;//temporary fix
    }

    //update post - localhost:3333/posts/{postId}
    @PostMapping("/posts/{postId}")
    public PostInfoDTO updatePost(@PathVariable("postId") int postId, @RequestBody PostInfoDTO postInfoDTO){
        return postService.updatePost(postId, postInfoDTO);//todo service
    }

    //delete post - localhost:3333/posts/{postId}
    @Transactional
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable("postId") int id){
        postService.deletePost(id);//todo service
    }
}
