package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.repositories.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostService extends AbstractService{
    @Autowired
    private PostRepository postRepository;//data base

    //add post
    public PostInfoDTO addPost(PostInfoDTO newPost, List<MultipartFile> images, MultipartFile video){//todo after service
        //validation with if statements for now
        //static method validation ValidationService class - images
        //stream for each image upload
        //save each each image (postId + url from upload method)
        //check if image upload is null

        //upload single video
        //save
        //MediaService.upload();//insert parameters into upload method
        Post p = mapper.map(newPost, Post.class);
        postRepository.save(p);
        return mapper.map(p, PostInfoDTO.class);
    }

    //get post by post_id
    public PostInfoDTO getPostById(int id) {
        return null;
    }

    //get posts by user_id
    public List<PostInfoDTO> getUserPosts(HttpSession session) {
        int userId = getUserId(session);
        return null;
    }

    //get all posts (for newsfeed)
    public List<PostInfoDTO> getPosts() {
        return null;
    }

    //update post
    public PostInfoDTO updatePost(int postId, PostInfoDTO postInfoDTO) {
        return null;
    }

    //delete post
    public void deletePost(int id) {
    }
}
