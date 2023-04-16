package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.user.SearchUDTO;
import com.example.travelleronline.model.DTOs.user.ChangePassDTO;
import com.example.travelleronline.model.DTOs.user.LoginDTO;
import com.example.travelleronline.model.DTOs.user.RegisterDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/userpass")
    public UserWithoutPassDTO changePass(@RequestBody ChangePassDTO changePassData, HttpSession s){
        isLogged(s);
        int id=(Integer) s.getAttribute("LOGGED_ID");
        userService.checkPassword(changePassData,id);
        if (!changePassData.getNewPassword().
                        equals(
                                changePassData.getConfirmNewPassword())){
            throw new BadRequestException("Confirm new pass must match new password.");
        }
         return userService.changePass(changePassData,id);
    }

    /*Hey buddy, i'm sure you would argue that this request should be a get request. However, i'm uncertain
     of how much different criterias there should be for the SEARCH, so i'm using a POST, and getting the Request Body
     for the parameters, instead of putting all of them into the url link*/
    @PostMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestBody SearchUDTO criteria) {
        List<User> users = userService.searchUsers(criteria);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users")
    public UserWithoutPassDTO deleteUserBySessionUserId(HttpSession s) {
        isLogged(s);
        int id=(Integer) s.getAttribute("LOGGED_ID");
        UserWithoutPassDTO u=userService.getById(id);
        userService.deleteUserById(id);
        return u;
    }

    @PutMapping("/users")
    public UserWithoutPassDTO updateUser(@RequestBody UserWithoutPassDTO userUpdateDTO,HttpSession s) {
        isLogged(s);
        userUpdateDTO = userService.updateUser(userUpdateDTO,getId(s));
        return userUpdateDTO;
    }


    private int getId(HttpSession s){
        return (Integer) s.getAttribute("LOGGED_ID");
    }
    private boolean isLogged(HttpSession s){
        Optional<Boolean> id=Optional.ofNullable((Boolean) s.getAttribute("LOGGED"));
        if(id.isPresent()){
            return true;
        }
        throw new UnauthorizedException("You have to Login");
        /*
        boolean logged;
        if(s.getAttribute("LOGGED")==null){
            logged=false;
        }else{
            logged =(boolean) s.getAttribute("LOGGED");
        }
        if(!logged){
            throw new UnauthorizedException("You have to Login");
        }
        return true;
         */
    }



}
