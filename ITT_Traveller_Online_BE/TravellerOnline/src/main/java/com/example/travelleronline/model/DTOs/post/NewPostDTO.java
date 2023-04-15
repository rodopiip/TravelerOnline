package com.example.travelleronline.model.DTOs.post;

import java.time.LocalDateTime;

public class NewPostDTO {
    private Integer userId;//question: Integer userId OR User user?
    private String title;//ok
    private String description;//ok
    private String location;//question: make a separate Location object?
    private String additionalInfo;
    private Integer categoryId;//question: Integer categoryId OR Category category?
    private String videoUrl;//ok
    private LocalDateTime dateCreated;//ok
}
