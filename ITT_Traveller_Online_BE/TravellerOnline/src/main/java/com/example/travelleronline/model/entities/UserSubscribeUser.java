package com.example.travelleronline.model.entities;

import com.example.travelleronline.model.entities.compositePKeys.UserAndUserCPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(UserAndUserCPK.class)
@Table(name = "users_subscribe_to_users")
@Getter
@Setter
public class UserSubscribeUser {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", referencedColumnName = "id", nullable = false)
    private User subscriber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribed_to_id", referencedColumnName = "id", nullable = false)
    private User subscribedTo;

    //getters and setters
}