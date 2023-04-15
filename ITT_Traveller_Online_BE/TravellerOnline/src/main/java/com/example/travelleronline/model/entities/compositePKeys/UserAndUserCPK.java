package com.example.travelleronline.model.entities.compositePKeys;

import com.example.travelleronline.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAndUserCPK implements Serializable {
    private User subscriber;
    private User subscribedTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAndUserCPK that)) return false;
        return getSubscriber().equals(that.getSubscriber()) && getSubscribedTo().equals(that.getSubscribedTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriber(), getSubscribedTo());
    }
}
