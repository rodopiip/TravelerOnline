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
    private Integer categoryId;//question: Integer categoryId OR Category category?
    private String videoUrl;//ok
    private List<String> imageUrls;
    private List<String> comments;//todo
    private LocalDateTime dateCreated;//ok

}
