package com.example.travelleronline.model.DTOs.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserWithoutPassDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String profilePhoto;
    private boolean verified;
    private String additionalInfo;
    private String gender;
}
