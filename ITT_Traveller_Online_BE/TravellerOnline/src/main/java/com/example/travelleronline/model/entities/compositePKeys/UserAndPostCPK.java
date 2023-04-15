package com.example.travelleronline.model.entities.compositePKeys;

import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserAndPostCPK implements Serializable {
    private User user;
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndPostCPK that = (UserAndPostCPK) o;
        return Objects.equals(user, that.user) && Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, post);
    }

}
