package com.example.travelleronline.model.DTOs.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentOfPostDTO {
    private int postId;
    private int userId;
    private String content;
}
