package com.example.travelleronline.model.DTOs.post;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//refactor: use record instead of pojo?
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostInfoDTO {
    private Integer id;//ok
    private UserWithoutPassDTO owner;
    //user id from session
    //private Integer userId;//question: Integer userId OR User user?
    private String title;//ok
    private String description;//ok
    private String location;//ok : question: make a separate Location object? no.
    private String additionalInfo;
    private Integer categoryId;
    private String videoUrl;//ok
    private List<String> imageUrls;
    private List<String> comments;//question: не мисля, че този списък е нужен
    private LocalDateTime dateCreated;//ok
    private long commentCount;
    private long reactionCount;
    private long rating;
}
