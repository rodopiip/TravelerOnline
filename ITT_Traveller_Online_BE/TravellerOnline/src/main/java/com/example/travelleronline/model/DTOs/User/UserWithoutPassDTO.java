package com.example.travelleronline.model.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithoutPassDTO {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String profile_photo;
    private boolean is_verified;
    private String gender;
}
