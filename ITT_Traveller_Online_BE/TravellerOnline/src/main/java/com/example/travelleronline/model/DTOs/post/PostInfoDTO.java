package com.example.travelleronline.model.DTOs.post;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostInfoDTO {
    private Integer id;//ok
    //private Integer userId;//question: Integer userId OR User user?
    private String title;//ok
    private String description;//ok
    private String location;//ok : question: make a separate Location object? no.
    private String additionalInfo;
    private Integer categoryId;//question: Integer categoryId OR Category category?
    private String videoUrl;//ok
    private LocalDateTime dateCreated;//ok

}
