package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/posts")
    public PostInfoDTO uploadPost(@RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("location") String location,
                                  @RequestParam("categoryId") int categoryId,
                                  @RequestParam("video") MultipartFile video,
                                  @RequestParam("image1") MultipartFile image1,
                                  @RequestParam("image2") MultipartFile image2,
                                  @RequestParam("image3") MultipartFile image3,
                                  HttpSession s){
        int userId = getLoggedId(s);
        //logger.debug(userId + " created a post");
        //logger.warn("WEIRD");
        return postService.uploadPost(userId, title, description, location, categoryId,
                                            video, image1, image2, image3);
    }
    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable("postId") int postId, HttpSession s){
        int userId = getLoggedId(s);
        return postService.deletePost(postId, userId);
    }

    @PostMapping("/test/posts")
    public Post returnPost(){
        return postService.testPost();
    }

}
