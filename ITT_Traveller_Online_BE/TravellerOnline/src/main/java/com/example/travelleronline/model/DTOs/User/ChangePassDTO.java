package com.example.travelleronline.model.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassDTO {
    String oldPassword;
    String newPassword;
    String confirmPassword;
}
