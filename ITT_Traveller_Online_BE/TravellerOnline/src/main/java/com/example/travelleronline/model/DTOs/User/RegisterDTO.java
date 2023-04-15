package com.example.travelleronline.model.DTOs.User;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// dto- as records?
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String first_name;

    private String last_name;

    private String password;
    private String confirm_password;
    private String email;

    private String phone_number;

    private LocalDate date_of_birth;

    private String profile_photo;

    private Boolean is_verified;

    private String additional_info;

    private String gender;

    private LocalDateTime dateAdded= LocalDateTime.now();
    private String verification_code;

}
