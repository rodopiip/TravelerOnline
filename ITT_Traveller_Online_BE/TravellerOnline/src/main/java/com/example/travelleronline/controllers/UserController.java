package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.User.ChangePassDTO;
import com.example.travelleronline.model.DTOs.User.LoginDTO;
import com.example.travelleronline.model.DTOs.User.RegisterDTO;
import com.example.travelleronline.model.DTOs.User.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.model.repositories.UserRepository;
import com.example.travelleronline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;

    @PostMapping("/users/auth")
    public UserWithoutPassDTO login(@RequestBody LoginDTO loginData,HttpSession s){
        UserWithoutPassDTO u= userService.login(loginData);
        s.setAttribute("LOGGED",true);
        s.setAttribute("LOGGED_ID",u.getId());
        return u;
    }
    @PostMapping("/users")
    public UserWithoutPassDTO register(@RequestBody RegisterDTO regData){
        return userService.register(regData);
    }
    @GetMapping("/users/{id}")
    public UserWithoutPassDTO getById(@PathVariable int id){
        return userService.getById(id);
    }
    @PutMapping
    public UserWithoutPassDTO changePass(@RequestBody ChangePassDTO changePassData, HttpSession s){
        boolean logged=(boolean) s.getAttribute("LOGGED");
        if(!logged){
            throw new UnauthorizedException("You have to Login");
        }
        return userService.changePass(changePassData);
    }



}
