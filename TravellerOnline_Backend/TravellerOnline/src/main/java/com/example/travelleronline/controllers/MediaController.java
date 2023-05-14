package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.exceptions.BadRequestException;
import com.example.travelleronline.service.MediaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
@Tag(name = "Media Controller", description = "Media, pictures and videos ednpoints.")
@RestController
public class MediaController extends AbstractController{
    @Autowired
    private MediaService mediaService;
    @PutMapping("/user/media")
    public UserWithoutPassDTO uploadProfilePic(@RequestParam("file")MultipartFile file, HttpSession session){
        return mediaService.changeProfilePic(file,getLoggedId(session));
    }
    @GetMapping("/media/{filename:.+}")
    public void downloadMedia(@PathVariable("filename") String fileName, HttpServletResponse response) {
        File responseFile=mediaService.downloadMedia(fileName);
        try {
            Files.copy(responseFile.toPath(), response.getOutputStream());
        }catch (IOException e){
            throw new BadRequestException("Error while loading the file");
        }
    }
}