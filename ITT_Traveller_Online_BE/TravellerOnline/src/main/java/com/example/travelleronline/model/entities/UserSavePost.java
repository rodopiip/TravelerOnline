package com.example.travelleronline.model.entities;

import com.example.travelleronline.model.entities.compositePKeys.UserAndPostCPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_save_posts")
@Getter
@Setter
@NoArgsConstructor
public class UserSavePost {
    @EmbeddedId
    private UserAndPostCPK id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public UserSavePost(User user, Post post) {
        this.user = user;
        this.post = post;
        this.id = new UserAndPostCPK(user.getId(), post.getId());
    }
}