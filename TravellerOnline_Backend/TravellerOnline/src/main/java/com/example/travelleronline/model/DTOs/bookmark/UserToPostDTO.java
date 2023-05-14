package com.example.travelleronline.model.DTOs.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserToPostDTO {
    private int userId;
    private int postId;
}
