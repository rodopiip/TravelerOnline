package com.example.travelleronline.model.DTOs.post;

import com.example.travelleronline.model.DTOs.comment.CommentDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostInfoDTO {
    private Integer id;
    private UserWithoutPassDTO owner;
    private String title;
    private String description;
    private String location;
    private String additionalInfo;
    private int categoryId;
    private String videoUrl;
    private List<String> imageUrls = new ArrayList<>();
    private List<CommentDTO> comments;
    private LocalDateTime dateCreated;
    private long commentCount;
    private long reactionCount;
    private long rating;
}
