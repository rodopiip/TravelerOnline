package com.example.travelleronline.model.DTOs.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String profilePhoto;
    private Boolean isVerified;
    private String additionalInfo;
    private String gender;
    private LocalDateTime dateAdded= LocalDateTime.now();
    private String verificationCode;
}
