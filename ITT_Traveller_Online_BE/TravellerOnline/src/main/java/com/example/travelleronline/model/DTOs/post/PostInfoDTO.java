package com.example.travelleronline.model.DTOs.post;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.Image;
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
<<<<<<< HEAD

    //user id from session
=======
>>>>>>> ce7c4a25691eb194c4b17f48757bf02a9716668b
    //private Integer userId;//question: Integer userId OR User user?
    private String title;//ok
    private String description;//ok
    private String location;//ok : question: make a separate Location object? no.
    private String additionalInfo;
    private Integer categoryId;//question: Integer categoryId OR Category category?
    private String videoUrl;//ok
    private LocalDateTime dateCreated;//ok

}
