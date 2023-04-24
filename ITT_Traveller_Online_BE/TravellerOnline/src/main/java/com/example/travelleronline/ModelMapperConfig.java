package com.example.travelleronline;

import com.example.travelleronline.model.DTOs.bookmark.UserToPostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.Image;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.entities.UserSavePost;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<UserSavePost, UserToPostDTO>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getId());
                map().setPostId(source.getPost().getId());
            }
        });

        return modelMapper;
    }
}
