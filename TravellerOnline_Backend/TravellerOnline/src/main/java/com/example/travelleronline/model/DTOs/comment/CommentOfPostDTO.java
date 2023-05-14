package com.example.travelleronline.model.DTOs.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentOfPostDTO {
    private int id;
    private Integer commentId;
    private int userId;
    private String content;
    private LocalDateTime dateAdded;
    private Set<CommentDTO> childComments;
}
