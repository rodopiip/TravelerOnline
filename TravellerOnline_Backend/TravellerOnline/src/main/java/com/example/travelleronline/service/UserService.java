package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.bookmark.UserToPostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.DTOs.user.*;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.entities.UserSavePost;
import com.example.travelleronline.exceptions.BadRequestException;

import com.example.travelleronline.model.repositories.UserSavePostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService{
    @Autowired
    private ValidationService validator;
    @Autowired
    private UserSavePostRepository bookmarkRepository;
    @Autowired
    private EmailService senderService;
    public String sendVerCode(String userMail){
        String verCode= String.valueOf(UUID.randomUUID());
        senderService.sendEmail(userMail,"Your verification link:",
                "localhost:3333/users/validate/verCode/"+verCode+"\n");
        return verCode;
    }
    public UserWithoutPassDTO validateVerCode(String verificationCode) {
        User u=userRepository.findByVerificationCode(verificationCode).orElseThrow(()->new BadRequestException("No such user"));
        u.setVerified(true);
        return mapper.map(userRepository.save(u),UserWithoutPassDTO.class);
    }
    public UserWithoutPassDTO login(LoginDTO loginData){
        Optional<User> opt= Optional.ofNullable(userRepository.findByEmail(loginData.getEmail()).orElseThrow(() -> new BadRequestException("No account with that email found")));
        User u=opt.get();
        if(!validator.isCorrectPassword(loginData.getPassword(), u.getPassword())) throw new BadRequestException("Incorrect password");

        return mapper.map(u, UserWithoutPassDTO.class);
    }
    public UserWithoutPassDTO register(RegisterDTO regData) {
        String email= regData.getEmail();
        if(!validator.isValidEmail(email)){
            throw new BadRequestException("Incorrect Email");
        }
        if(!validator.isValidNumber(regData.getPhoneNumber())){
            throw new BadRequestException("Incorrect phone number");
        }
        if(!validator.isValidPassword(regData.getPassword())){
            throw new BadRequestException("Password must contain:" +
                    "At least 8 characters long. " +
                    "Contains at least one uppercase letter. " +
                    "Contains at least one lowercase letter. " +
                    "Contains at least one digit. " +
                    "Contains at least one special character");
        }
        if(!(regData.getPassword().equals( regData.getConfirmPassword()))){
            throw new BadRequestException("Passwords must match");
        }
        if(userRepository.existsByEmail(email)){
            throw new BadRequestException("Email already in use");
        }
        User u= mapper.map(regData,User.class);
        u.setPassword(validator.encodePassword(u.getPassword()));
        u.setVerified(false);
        u.setVerificationCode(sendVerCode(u.getEmail()));

        userRepository.save(u);
        return mapper.map(u,UserWithoutPassDTO.class);
    }
    public UserWithoutPassDTO changePass(ChangePassDTO changePassData, int userId){
        checkPassword(changePassData,userId);
        if (!changePassData.getNewPassword().
                equals(
                        changePassData.getConfirmNewPassword())){
            throw new BadRequestException("Confirm new pass must match new password.");
        }
        User u=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new BadRequestException("No account found"))).get();
        u.setPassword(validator.encodePassword(changePassData.getNewPassword()));
        userRepository.save(u);
        return mapper.map(u, UserWithoutPassDTO.class);
    }
    public List<UserWithoutPassDTO> searchUsers(SearchUDTO criteria) {
        return userRepository.searchUsers(
                criteria.getFirstName(),
                criteria.getLastName(),
                criteria.getEmail(),
                criteria.getPhoneNumber()
        ).stream().map(user -> mapper.map(user,UserWithoutPassDTO.class))
                .collect(Collectors.toList());
    }
    public UserWithoutPassDTO deleteUserById(int userId) {
        User u=userRepository.findById(userId).get();
        userRepository.deleteById(userId);
        return mapper.map(u,UserWithoutPassDTO.class);
    }

    public UserWithoutPassDTO updateUser(UserWithoutPassDTO updateData, int userId) {
        User u = Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new BadRequestException("No account found"))).get();;
        u.setFirstName(updateData.getFirstName());
        u.setLastName(updateData.getLastName());
        if(validator.isValidEmail(updateData.getEmail())){
            u.setEmail(updateData.getEmail());
        }
        if(validator.isValidNumber(updateData.getPhoneNumber())){
            u.setPhoneNumber(updateData.getPhoneNumber());
        }
        u.setDateOfBirth(LocalDate.parse(updateData.getDateOfBirth()));
        u.setProfilePhoto(updateData.getProfilePhoto());
        u.setAdditionalInfo(updateData.getAdditionalInfo());
        u.setGender(updateData.getGender());
        userRepository.save(u);
        return mapper.map(u,UserWithoutPassDTO.class);
    }

    @Transactional
    public String subscribe(int subscriberId, int subscribedToId){
        if(subscriberId==subscribedToId){
            throw new BadRequestException("You cannot subscribe to yourself");
        }
        if(userRepository.subscriptionExist(subscribedToId,subscriberId)>0){
                userRepository.deleteSubscription(subscribedToId,subscriberId);
            return ("Unsubscribed successfully");
            }else{
                userRepository.addSubscription(subscribedToId,subscriberId);
            return ("Subscribed successfully");
            }
    }
    private boolean checkPassword(ChangePassDTO changeData,int userId){
        //might as well be void though
        User u=getUserFromId(userId);
        if(!validator.isCorrectPassword(changeData.getOldPassword(), u.getPassword())) throw new BadRequestException("Incorrect password");
        return true;
    }
    private User getUserFromId(int userId){
        return Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new BadRequestException("No user found"))).get();
    }

    public UserWithoutPassDTO userWithouPassById(int userId) {
        return mapper.map(userRepository.findById(userId),UserWithoutPassDTO.class);
    }

    public UserWithoutPassDTO getUserWithSubById(int userId) {
        return mapper.map(userRepository.findById(userId).orElseThrow(()->new BadRequestException("Such user doesn't exist")), UserWithSub.class);
    }

    public List<UserWithoutPassDTO> getSubscribers(int subscribedTo) {
        User u=userRepository.findById(subscribedTo).get();
        List<UserWithoutPassDTO> list=  new ArrayList<>();
        //return mapper.map(u, UserWithoutPassDTO.class);
        if(u.getSubscribers().isEmpty()) throw new BadRequestException("No Subscribers");
        u.getSubscribers().forEach(user -> list.add(mapper.map(user,UserWithoutPassDTO.class)));
        return list;
    }
    public List<UserWithoutPassDTO> getSubscribedTo(int subscriber) {
        User u=userRepository.findById(subscriber).get();
        List<UserWithoutPassDTO> list= new ArrayList<>();
        //return mapper.map(u, UserWithoutPassDTO.class);
        if(u.getSubscribedTo().isEmpty()) throw new BadRequestException("No Subscriptions");
        u.getSubscribedTo().forEach(user -> list.add(mapper.map(user,UserWithoutPassDTO.class)));
        return list;
    }


    public UserToPostDTO bookmark(int userId, int postId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<UserSavePost> userSavePost = bookmarkRepository.
                findByUserIdAndPostId(userId, postId);
        if(userSavePost.isPresent()){
            bookmarkRepository.delete(userSavePost.get());
            return mapper.map(userSavePost.get(),UserToPostDTO.class);
        }else {
            UserSavePost newBookmark = new UserSavePost(user, post);
            return mapper.map(bookmarkRepository.save(newBookmark),UserToPostDTO.class);
        }
    }
    public List<PostInfoDTO> bookmarkList(int loggedId){
        return postRepository.getTheBookmarkedPosts(loggedId).stream()
                .map(post -> mapper.map(post,PostInfoDTO.class))
                .collect(Collectors.toList());
    }

    public UserWithoutPassDTO resetPass(String mail) {
        User u=userRepository.findByEmail(mail).orElseThrow(()->new BadRequestException("No user with such email"));
        String newPass=String.valueOf(UUID.randomUUID());
        u.setPassword(ValidationService.encodePassword(newPass));
        senderService.sendEmail(mail,"Your new password.","Your new password is:\n"+newPass);
        userRepository.save(u);
        return mapper.map(u,UserWithoutPassDTO.class);
    }
}
