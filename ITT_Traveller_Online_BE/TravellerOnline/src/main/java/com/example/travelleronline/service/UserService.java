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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ValidationService validator;

    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public UserWithoutPassDTO login(LoginDTO loginData){
        User u=userRepository.findByEmail(loginData.getEmail());
        if(u==null) throw new BadRequestException("No account with that email found");
        System.out.println(u.getPassword());
        if(!validator.isValidPassword(loginData.getPassword(), u.getPassword())) throw new BadRequestException("Incorrect password");

        return mapper.map(u, UserWithoutPassDTO.class);
    }
    public UserWithoutPassDTO register(RegisterDTO regData) {
        String email= regData.getEmail();
        //fix for different exceptions
        if(userRepository.existsByEmail(email)){
           throw new BadRequestException("Email already in use");
        }
        if(!validator.IsValidEmail(email)){
            throw new BadRequestException("Incorrect Email");
        }
        if(!(regData.getPassword().equals( regData.getConfirm_password()))){
            throw new BadRequestException("Passwords must match");
        }
        User u= mapper.map(regData,User.class);
        u.setPassword(validator.encodePassword(u.getPassword()));
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

    public UserWithoutPassDTO changePass(ChangePassDTO changePassData,int id){
        User u=userRepository.findById(id);
        u.setPassword(changePassData.getNew_password());
        userRepository.save(u);
        return mapper.map(u, UserWithoutPassDTO.class);
    }


    //ne se raboti sys sesii,response ili request

}
