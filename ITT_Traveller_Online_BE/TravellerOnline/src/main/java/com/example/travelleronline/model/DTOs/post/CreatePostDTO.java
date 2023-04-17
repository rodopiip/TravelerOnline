package com.example.travelleronline.model.DTOs.post;

import com.example.travelleronline.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDTO {
    private Integer id;
    private User owner;
    //private int ownerId;//question: int ownerId OR User owner???
    private String title;
    private String description;
    private String location;
    private String additionalInfo;
    private Integer categoryId;
    private String videoUrl;
    private LocalDateTime dateCreated;
}
