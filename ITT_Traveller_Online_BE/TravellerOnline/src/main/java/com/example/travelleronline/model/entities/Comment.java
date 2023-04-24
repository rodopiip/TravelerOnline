package com.example.travelleronline.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import jakarta.validation.constraints.Size;
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
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 300)
    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY /*cascade = CascadeType.ALL this is in the database constrain*/)
    private Set<Comment> childComments = new HashSet<>();

    @Column(name = "rating", nullable = false)
    @Builder.Default
    private Integer rating = 0;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;
}