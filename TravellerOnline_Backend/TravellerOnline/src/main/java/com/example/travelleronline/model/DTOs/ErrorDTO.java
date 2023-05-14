package com.example.travelleronline.model.DTOs;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class ErrorDTO {

    private String msg;
    private int status;
    private LocalDateTime time;
}
