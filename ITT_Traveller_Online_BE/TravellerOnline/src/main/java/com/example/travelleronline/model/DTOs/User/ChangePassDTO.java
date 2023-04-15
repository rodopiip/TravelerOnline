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
    String old_password;
    String new_password;
    String confirm_password;
}
