package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import lombok.*;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data //equals(), hashCode() and toString();
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "super_comment_id")
    private Integer superCommentId;

    @Column(name = "rating", nullable = false)
    @Builder.Default
    private Integer rating = 0;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

}