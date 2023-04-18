package com.example.travelleronline.model.DTOs.user;

import com.example.travelleronline.model.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithSub extends UserWithoutPassDTO {

    private Set<UserWithoutPassDTO> subscribers=new HashSet<>();
    private Set<UserWithoutPassDTO> subscribedTo=new HashSet<>();
}
