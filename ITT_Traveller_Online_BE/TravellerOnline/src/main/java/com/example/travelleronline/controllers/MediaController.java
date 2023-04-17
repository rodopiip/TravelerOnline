package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.service.MediaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@RestController
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/user/media")
    public UserWithoutPassDTO uploadProfilePic(@RequestParam("file")MultipartFile file, HttpSession session){
        return mediaService.changeProfilePic(file,session);
    }
    @SneakyThrows
    @GetMapping("/media/{filename}")
    public void downloadMedia(@PathVariable("filename") String fileName, HttpServletResponse response){
        File img=mediaService.downloadMedia(fileName);
        Files.copy(img.toPath(),response.getOutputStream());
        return;
    }
}
