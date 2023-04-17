package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.user.SearchUDTO;
import com.example.travelleronline.model.DTOs.user.ChangePassDTO;
import com.example.travelleronline.model.DTOs.user.LoginDTO;
import com.example.travelleronline.model.DTOs.user.RegisterDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.service.AbstractService;
import com.example.travelleronline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;

    public static int getId(HttpSession s) {
        return 0;
    }

    @PostMapping("/users/auth")
    public UserWithoutPassDTO login(@RequestBody LoginDTO loginData,HttpSession s){
        UserWithoutPassDTO u = userService.login(loginData);
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

    @PutMapping("/userpass")
    public UserWithoutPassDTO changePass(@RequestBody ChangePassDTO changePassData, HttpSession s){
         return userService.changePass(changePassData,s);
    }

    /*Hey buddy, i'm sure you would argue that this request should be a GET request. However, i'm uncertain
     of how much different criteria there should be for the SEARCH, so i'm using a POST, and getting the Request Body
     for the parameters, instead of putting all of them into the url link*/
    @PostMapping("/users/search")
    public List<UserWithoutPassDTO> searchUsers(@RequestBody SearchUDTO criteria) {
        List<UserWithoutPassDTO> users = userService.searchUsers(criteria);
        return users;
    }

    @DeleteMapping("/users")
    public UserWithoutPassDTO deleteUserBySessionUserId(HttpSession s) {
        return userService.deleteUserById(s);
    }

    @PutMapping("/users")
    public UserWithoutPassDTO updateUser(@RequestBody UserWithoutPassDTO userUpdateDTO,HttpSession s) {
        return userService.updateUser(userUpdateDTO,s);
    }

    @PostMapping("/users/subscribe/{subscribe_to_user}")
    public void subscribe(HttpSession s,
                          @PathVariable("subscribe_to_user") int subscribedToId
                            ) {
        userService.subscribe(s, subscribedToId);

        return;
    }

    //USER-POST MAPPINGS

    //get saved posts by user - localhost:3333/users/saved-posts
    //todo

    //add saved post - localhost:3333/users/saved-posts
    //todo

    //delete saved post - localhost:3333/users/saved-posts/{postId}
    //todo



}
