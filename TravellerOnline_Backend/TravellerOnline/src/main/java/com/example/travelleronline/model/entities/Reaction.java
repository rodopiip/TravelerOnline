package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

}