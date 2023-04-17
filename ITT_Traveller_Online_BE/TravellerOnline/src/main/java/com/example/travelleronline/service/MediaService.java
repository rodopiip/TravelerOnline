package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class MediaService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    private User getUserBySession(HttpSession session){
        return userRepository.findById(SessionService.getUserId(session));
    }

    @SneakyThrows
    public UserWithoutPassDTO changeProfilePic(MultipartFile file, HttpSession session){
        String ext= FilenameUtils.getExtension(file.getOriginalFilename());
        String uplName= UUID.randomUUID()+"."+ext;
        File folder=new File("uploads");
        if(!folder.exists()){
            folder.mkdirs();
        }

        File upload=new File(folder,uplName);
        Files.copy(file.getInputStream(),upload.toPath(), StandardCopyOption.REPLACE_EXISTING);
        String url= folder.getName()+File.separator+ upload.getName();
        User user=getUserBySession(session);
        user.setProfilePhoto(url);
        userRepository.save(user);
        return mapper.map(user, UserWithoutPassDTO.class);
    }
    public void uploadVideo(){
        return;
    }
    public File downloadImage(String fileName){
        File folder=new File("uploads");
        File f=new File(folder,fileName);
        if(f.exists()){
            return f;
        }else {
            throw new NotFoundException("File not Found");
        }
    }
    public void downloadVideo(){
        return;
    }

}
