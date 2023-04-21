package com.example.travelleronline.service;

import com.example.travelleronline.model.repositories.PostRepository;
import com.example.travelleronline.model.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class AbstractService {
    @Autowired
    protected ModelMapper mapper;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PostRepository postRepository;

}
