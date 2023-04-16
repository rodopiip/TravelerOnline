package com.example.travelleronline.model.DTOs.userToUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserToUserDTO {
    private Integer subscriberId;
    private Integer subscribedToId;


}
