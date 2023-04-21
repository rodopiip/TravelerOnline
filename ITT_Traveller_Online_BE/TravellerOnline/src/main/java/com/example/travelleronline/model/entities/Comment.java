package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Data //equals(), hashCode() and toString();
public class Comment {

    //todo:Refactor comment, to use relationship dependency
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Size(min = 1, max = 300)
    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @Column(name = "super_comment_id")
    private Integer superCommentId;

    /*
    @ManyToOne
    @JoinColumn(name = "super_comment_id")
    private Comment parentComment;
    */


    @Column(name = "rating", nullable = false)
    @Builder.Default
    private Integer rating = 0;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

/*
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL,
                                                    orphanRemoval = true
                                        //if the parent comment is deleted, the children are also deleted
                                        //todo:maybe refactor the delete service, no longer needs to be with recursion
             )
    private Set<Comment> replies=new HashSet<>();
*/
}