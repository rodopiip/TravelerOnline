package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.DTOs.post.SearchPostDTO;
import com.example.travelleronline.model.DTOs.post.SearchPostResultDTO;
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
    @GetMapping("/posts")
    public Page<PostInfoDTO> getPosts(@RequestParam(name = "page", defaultValue = "0") int pageNumber){
        return postService.getAllPostsWithPagination(pageNumber);
    }
    @GetMapping("/posts/{id}")
    public PostInfoDTO getPostById(@PathVariable("id") int id){
        return postService.getPostById(id);
    }

    @GetMapping("/users/{userId}/posts")
    public Page<PostInfoDTO> getUserPosts(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                    @PathVariable int userId){
        return postService.getUserPosts(pageNumber,userId);
    }
    @GetMapping("/users/posts")
    public Page<PostInfoDTO> getUserPosts(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                            HttpSession session){
        return postService.getUserPosts(pageNumber,getLoggedId(session));
    }
    @PostMapping("/posts")
    public PostInfoDTO uploadPost(@RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("location") String location,
                                  @RequestParam("categoryId") int categoryId,
                                  @RequestParam(name = "additionalInfo", required = false) String additionalInfo,
                                  @RequestParam(name = "video", required = false) MultipartFile video,
                                  @RequestParam(name = "image1", required = false) MultipartFile image1,
                                  @RequestParam(name = "image2", required = false) MultipartFile image2,
                                  @RequestParam(name = "image3", required = false) MultipartFile image3,
                                  HttpSession s){
        int userId = getLoggedId(s);
        return postService.uploadPost(userId, title, description, location, categoryId,
                video, image1, image2, image3, additionalInfo);
    }
    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable("postId") int postId, HttpSession s){
        int userId = getLoggedId(s);
        return postService.deletePost(postId, userId);
    }
    @GetMapping("/posts/{postId}/location")
    public String getLocationUrl(@PathVariable int postId){
        return postService.getLocationUrl(postId);
    }
    @GetMapping("/newsfeedbydate")
    public Page<PostInfoDTO> newsfeedbydate(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                            HttpSession session){
        return postService.getNewsfeedByDate(pageNumber,getLoggedId(session));
    }
    @GetMapping("/newsfeedbyrating")
    public Page<PostInfoDTO> newsfeedbyrating(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                              HttpSession session) {
        return postService.getNewsfeedByRating(pageNumber, getLoggedId(session));
    }
    @PutMapping("/posts/{postId}")
    public PostInfoDTO editPost(@PathVariable("postId") int postId,
                                @RequestBody PostInfoDTO postInfoDTO,
                                HttpSession s){
        int userId = getLoggedId(s);
        return postService.editPost(userId, postId, postInfoDTO);
    }
    @PostMapping("/posts/name_search")
    public Page<SearchPostResultDTO>searchPostsByTitle(@RequestBody SearchPostDTO searchPostDTO,
                                                       @RequestParam(name = "page", defaultValue = "0") int pageNumber)
    {
        return postService.searchPostsByTitle(searchPostDTO, pageNumber);
    }
    @PostMapping("/posts/category_search")
    public Page<SearchPostResultDTO>searchPostsByCategories(@RequestBody SearchPostDTO searchPostDTO,
                                                            @RequestParam(name = "page", defaultValue = "0") int pageNumber)
    {
        return postService.searchPostsByCategories(searchPostDTO, pageNumber);
    }
}