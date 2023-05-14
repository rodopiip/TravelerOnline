package com.example.travelleronline;
import com.example.travelleronline.model.DTOs.bookmark.UserToPostDTO;
import com.example.travelleronline.model.entities.UserSavePost;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
