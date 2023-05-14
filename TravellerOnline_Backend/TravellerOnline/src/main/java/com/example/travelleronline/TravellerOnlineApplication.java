package com.example.travelleronline;

import com.example.travelleronline.model.DTOs.bookmark.UserToPostDTO;
import com.example.travelleronline.model.entities.UserSavePost;
import com.example.travelleronline.service.EmailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TravellerOnlineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravellerOnlineApplication.class, args);
    }


}
