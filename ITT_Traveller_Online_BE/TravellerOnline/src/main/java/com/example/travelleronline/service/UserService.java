package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.User.ChangePassDTO;
import com.example.travelleronline.model.DTOs.User.LoginDTO;
import com.example.travelleronline.model.DTOs.User.RegisterDTO;
import com.example.travelleronline.model.DTOs.User.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    public UserWithoutPassDTO login(LoginDTO loginData){
        return null;
    }
    public UserWithoutPassDTO register(RegisterDTO regData) {
        //fix for different exceptions
        if(userRepository.existsByEmail(regData.getEmail())){
           throw new BadRequestException("Email already in use");
        }
        if(!(regData.getPassword().equals( regData.getConfirm_password()))){
            throw new BadRequestException("Passwords must match");
        }
        User u= mapper.map(regData,User.class);
        userRepository.save(u);
        return mapper.map(u,UserWithoutPassDTO.class);
    }

    public UserWithoutPassDTO getById(int id) {
        User u=userRepository.findById(id);
        if(u==null){
            throw new NotFoundException("User cannot be found");
        }
        return mapper.map(u,UserWithoutPassDTO.class);
    }

    public UserWithoutPassDTO changePass(ChangePassDTO changePassData){
        return null;
    }


    //ne se raboti sys sesii,response ili request

}
