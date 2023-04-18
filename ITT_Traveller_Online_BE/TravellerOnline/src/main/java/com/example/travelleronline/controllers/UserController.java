package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.user.SearchUDTO;
import com.example.travelleronline.model.DTOs.user.ChangePassDTO;
import com.example.travelleronline.model.DTOs.user.LoginDTO;
import com.example.travelleronline.model.DTOs.user.RegisterDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;

    @PostMapping("/users/auth")
    public UserWithoutPassDTO login(@RequestBody LoginDTO loginData,HttpSession session){
        UserWithoutPassDTO u = userService.login(loginData);
        session.setAttribute("LOGGED",true);
        session.setAttribute("LOGGED_ID",u.getId());
        return u;
    }
    @DeleteMapping("/logout")
    public String logout(HttpSession session){
        if(checkIfLogged(session)){
            session.setAttribute("LOGGED",false);
            session.setAttribute("LOGGED_ID",0);
            throw new UnauthorizedException("Successfully logged out");
        }
        throw new UnauthorizedException("You need to be logged, to be able to log out");
    }

    @PostMapping("/users")
    public UserWithoutPassDTO register(@RequestBody RegisterDTO regData){
        return userService.register(regData);
    }

    @GetMapping("/users/{userId}")
    public UserWithoutPassDTO showUserById(@PathVariable int userId){
        //return userService.userWithouPassById(userId);
        return userService.userWithSubById(userId);
    }

    @PutMapping("/userpass")
    public UserWithoutPassDTO changePass(@RequestBody ChangePassDTO changePassData, HttpSession session){
         return userService.changePass(changePassData, getLoggedId(session));
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
    public UserWithoutPassDTO deleteUserBySessionUserId(HttpSession session) {
        return userService.deleteUserById(getLoggedId(session));
    }

    @PutMapping("/users")
    public UserWithoutPassDTO updateUser(@RequestBody UserWithoutPassDTO userUpdateDTO,HttpSession session) {
        return userService.updateUser(userUpdateDTO, getLoggedId(session));
    }

    @PostMapping("/users/subscribe/{subscribe_to_user}")
    public String subscribe(HttpSession session,
                          @PathVariable("subscribe_to_user") int subscribedToId
                            ) {
        return "Subscribers:"+userService.subscribe(getLoggedId(session), subscribedToId);

    }
    @GetMapping("/users/{subscribedTo}/subscribers")
    public List<UserWithoutPassDTO> getSubscribers(@PathVariable int subscribedTo){
        return userService.getSubscribers(subscribedTo);
    }
    @GetMapping("/users/{subscriber}/subscribedTo")
    public List<UserWithoutPassDTO> getSubscriptions(@PathVariable int subscriber){
        return userService.getSubscribedTo(subscriber);
    }

    //USER-POST MAPPINGS

    //get saved posts by user - localhost:3333/users/saved-posts
    //todo

    //add saved post - localhost:3333/users/saved-posts
    //todo

    //delete saved post - localhost:3333/users/saved-posts/{postId}
    //todo



}
