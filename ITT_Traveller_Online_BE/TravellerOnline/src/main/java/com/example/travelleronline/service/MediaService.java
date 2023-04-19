package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.repositories.UserRepository;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class MediaService extends AbstractService{

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    public static String uploadMedia(MultipartFile file){
        String ext= FilenameUtils.getExtension(file.getOriginalFilename());
        String uplName= UUID.randomUUID()+"."+ext;
        File folder=new File("uploads");
        if(!folder.exists()){
            folder.mkdirs();
        }
        File upload=new File(folder,uplName);
        Files.copy(file.getInputStream(),upload.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return folder.getName()+File.separator+ upload.getName();
    }
    public static Boolean deleteMedia(String URL){
        File folder=new File("uploads");
        File toDelete=new File(folder,URL);
        toDelete.delete();
        return true;
    }

    public UserWithoutPassDTO changeProfilePic(MultipartFile file,int userId){
        String url=uploadMedia(file);
        //maybe validate it's image?
        User user=userRepository.findById(userId).orElseThrow(()->new BadRequestException("User does not exist anymore"));
        user.setProfilePhoto(url);
        userRepository.save(user);
        return mapper.map(user, UserWithoutPassDTO.class);
    }

    public File downloadMedia(String fileName){
        File folder=new File("uploads");
        File f=new File(folder,fileName);
        if(f.exists()){
            return f;
        }else {
            throw new NotFoundException("File not Found");
        }
    }
}
