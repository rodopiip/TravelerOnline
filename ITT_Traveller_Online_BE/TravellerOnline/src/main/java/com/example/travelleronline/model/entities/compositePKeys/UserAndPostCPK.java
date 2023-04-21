package com.example.travelleronline.model.entities.compositePKeys;

import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class UserAndPostCPK implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_id")
    private int postId;

}
