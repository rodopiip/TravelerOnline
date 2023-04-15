package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.post.NewPostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;
    public PostInfoDTO addPost(NewPostDTO newPost){
        //validation with if statements for now
        Post p = mapper.map(newPost, Post.class);
        postRepository.save(p);
        return mapper.map(p, PostInfoDTO.class);
    }
}
