package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.bookmark.UserToPostDTO;
import com.example.travelleronline.model.DTOs.user.SearchUDTO;
import com.example.travelleronline.model.DTOs.user.ChangePassDTO;
import com.example.travelleronline.model.DTOs.user.LoginDTO;
import com.example.travelleronline.model.DTOs.user.RegisterDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.entities.UserSavePost;
import com.example.travelleronline.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller ", description = "User endpoints")
@RestController
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;

    @PostMapping("/users/auth")
    public UserWithoutPassDTO login(@Valid @RequestBody LoginDTO loginData, HttpSession session){
        UserWithoutPassDTO u = userService.login(loginData);
        session.setAttribute("LOGGED_ID",u.getId());

        return u;
    }
    @PostMapping("/users/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

    @PostMapping("/users")
    public UserWithoutPassDTO register(@Valid @RequestBody RegisterDTO regData){
        return userService.register(regData);
    }

    @GetMapping("/users/{userId}")
    public UserWithoutPassDTO showUserById(@PathVariable int userId){
        //return userService.userWithouPassById(userId);
        return userService.userWithSubById(userId);
    }

    @PutMapping("/userpass")
    public UserWithoutPassDTO changePass(@Valid @RequestBody ChangePassDTO changePassData, HttpSession session){
         return userService.changePass(changePassData, getLoggedId(session));
    }
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
    public UserWithoutPassDTO updateUser(@Valid @RequestBody UserWithoutPassDTO userUpdateDTO,HttpSession session) {
        return userService.updateUser(userUpdateDTO, getLoggedId(session));
    }
    @PostMapping("/users/subscribe/{subscribe_to_user}")
    public String subscribe(HttpSession session,
                          @PathVariable("subscribe_to_user") int subscribedToId
                            ) {
        return "Subscribers:"+userService. subscribe(getLoggedId(session), subscribedToId);
    }
    @GetMapping("/users/{subscribedTo}/subscribers")
    public List<UserWithoutPassDTO> getSubscribers(@PathVariable int subscribedTo){
        return userService.getSubscribers(subscribedTo);
    }
    @GetMapping("/users/{subscriber}/subscribedTo")
    public List<UserWithoutPassDTO> getSubscriptions(@PathVariable int subscriber){
        return userService.getSubscribedTo(subscriber);
    }

    @PostMapping("/users/bookmark/{postId}")
    public UserToPostDTO bookmarkPost(@PathVariable int postId, HttpSession session) {
        return userService.bookmark(getLoggedId(session), postId);
    }

    @GetMapping("/users/bookmark")
    public List<UserToPostDTO> bookmarkList(HttpSession session) {
        return userService.bookmarkList(getLoggedId(session));
    }
    @GetMapping("users/{userId}/bookmark")
    public List<UserToPostDTO> bookmarkPost(@PathVariable int userId) {
        return userService.bookmarkList(userId);
    }

    @GetMapping("users/validate/token/{token}")
    public UserWithoutPassDTO valdiateToken(@PathVariable String token) {
        return userService.validateToken(token);
    }


}
