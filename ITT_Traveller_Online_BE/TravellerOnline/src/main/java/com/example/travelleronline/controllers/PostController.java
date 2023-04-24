package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Post Controller", description = "Post endpoints")
@RestController
public class PostController extends AbstractController{

    @Autowired
    private PostService postService;
    //todo pageable
    @GetMapping("/posts")
    public Page<PostInfoDTO> getPosts(@RequestParam(name = "post", defaultValue = "0") int pageNumber){
        //todo Spring validation for criteria?
        return postService.getAllPostsWithPagination(pageNumber);
    }
    @GetMapping("/posts/{id}")
    public PostInfoDTO getPostById(@PathVariable("id") int id){
        return postService.getPostById(id);//todo service
    }
    @GetMapping("/users/{user-id}/posts")
    public List<PostInfoDTO> getUserPosts(@PathVariable("user-id") int userId){
        return postService.getUserPosts(userId);//todo service
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
        return postService.uploadPost(userId, title, description, location, categoryId,
                video, image1, image2, image3);
        //logger.debug(userId + " created a post");todo refactor
        //logger.warn("WEIRD");todo refactor
    }
    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable("postId") int postId, HttpSession s){
        int userId = getLoggedId(s);
        //question: има ли нужда от параметър userId в `postService.deletePost(postId, userId)`?
        return postService.deletePost(postId, userId);
    }

    @GetMapping("/posts/{postId}/location")
    public String getLocationUrl(@PathVariable int postId){
        return postService.getLocationUrl(postId);
    }
    @PutMapping("/posts/{postId}")
    public PostInfoDTO editPost(@PathVariable("postId") int postId,
                                @RequestBody PostInfoDTO postInfoDTO,
                                HttpSession s){
        //todo validation parameters in entity. HOW?
        int userId = getLoggedId(s);
        return postService.editPost(userId, postId, postInfoDTO);
    }
}

