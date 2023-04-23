package com.example.travelleronline.model.DTOs.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserWithoutPassDTO {

    /*
    DTO without lists:
    prevention of circular list invocation
    (circular reference) : composition both ways
     */
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
