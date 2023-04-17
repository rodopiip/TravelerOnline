package com.example.travelleronline.model.DTOs.post;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//refactor: use record instead of pojo?
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostInfoDTO {
    private Integer id;//ok
    private UserWithoutPassDTO owner;
    private String title;//ok
    private String description;//ok
    private String location;//ok : question: make a separate Location object? no.
    private String additionalInfo;
    private Integer categoryId;//question: Integer categoryId OR Category category?
    private String videoUrl;//ok
    private LocalDateTime dateCreated;//ok

}
