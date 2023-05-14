package com.example.travelleronline.model.DTOs.post;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchPostResultDTO {
    private Integer id;
    private int ownerId;
    private String title;
    private String location;
    private int categoryId;
    private List<String> imageUrls = new ArrayList<>();
    private LocalDateTime dateCreated;
}
