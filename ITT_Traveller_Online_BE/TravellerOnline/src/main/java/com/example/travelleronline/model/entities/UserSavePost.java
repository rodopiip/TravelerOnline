package com.example.travelleronline.model.entities;

import com.example.travelleronline.model.entities.compositePKeys.UserAndPostCPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(UserAndPostCPK.class)
@Table(name = "users_save_posts")
@Getter
@Setter
public class UserSavePost {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;
}