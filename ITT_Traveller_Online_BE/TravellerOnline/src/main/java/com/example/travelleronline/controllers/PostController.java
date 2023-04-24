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
    public Page<PostInfoDTO> getPosts(@RequestParam(name = "post", defaultValue = "0") int pageNumber){
        return postService.getAllPostsWithPagination(pageNumber);
    }
    @GetMapping("/posts/{id}")
    public PostInfoDTO getPostById(@PathVariable("id") int id){
        return postService.getPostById(id);//todo service
    }

    @GetMapping("/users/{userId}/posts")
    public List<PostInfoDTO> getUserPosts(@PathVariable int userId){
        return postService.getUserPosts(userId);//todo service
    }
    @GetMapping("/users/posts")
    public List<PostInfoDTO> getUserPosts(HttpSession session){
        return postService.getUserPosts(getLoggedId(session));//todo service
    }
    @PostMapping("/posts")
    public PostInfoDTO uploadPost(@RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("location") String location,
                                  @RequestParam("categoryId") int categoryId,
                                  @RequestParam("additionalInfo") String additionalInfo,
                                  @RequestParam("video") MultipartFile video,
                                  @RequestParam("image1") MultipartFile image1,
                                  @RequestParam("image2") MultipartFile image2,
                                  @RequestParam("image3") MultipartFile image3,
                                  HttpSession s){
        int userId = getLoggedId(s);
        return postService.uploadPost(userId, title, description, location, categoryId,
                video, image1, image2, image3, additionalInfo);
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
        //todo validation parameters in entity. HOW?
        int userId = getLoggedId(s);
        return postService.editPost(userId, postId, postInfoDTO);
    }
    @PostMapping("/posts/name_search")
    public Page<SearchPostResultDTO>searchPostsByTitle(@RequestBody SearchPostDTO searchPostDTO,
                                                       @RequestParam(name = "page", defaultValue = "0") int pageNumber)
    {
        System.out.println("test 1");
        return postService.searchPostsByTitle(searchPostDTO, pageNumber);
    }
    @PostMapping("/posts/category_search")
    public Page<SearchPostResultDTO>searchPostsByCategories(@RequestBody SearchPostDTO searchPostDTO,
                                                            @RequestParam(name = "page", defaultValue = "0") int pageNumber)
    {
        System.out.println("test 2");
        return postService.searchPostsByCategories(searchPostDTO, pageNumber);
    }
}