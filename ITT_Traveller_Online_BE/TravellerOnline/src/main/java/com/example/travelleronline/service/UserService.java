package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.user.SearchUDTO;
import com.example.travelleronline.model.DTOs.user.ChangePassDTO;
import com.example.travelleronline.model.DTOs.user.LoginDTO;
import com.example.travelleronline.model.DTOs.user.RegisterDTO;
import com.example.travelleronline.model.DTOs.user.UserWithoutPassDTO;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ValidationService validator;

    //BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public UserWithoutPassDTO login(LoginDTO loginData){
        User u=userRepository.findByEmail(loginData.getEmail());
        if(u==null) throw new BadRequestException("No account with that email found");
        //System.out.println(u.getPassword());
        if(!validator.isCorrectPassword(loginData.getPassword(), u.getPassword())) throw new BadRequestException("Incorrect password");

        return mapper.map(u, UserWithoutPassDTO.class);
    }
    public boolean checkPassword(ChangePassDTO changeData,int id){
        //might as well be void though
        User u=userRepository.findById(id);
        if(!validator.isCorrectPassword(changeData.getOldPassword(), u.getPassword())) throw new BadRequestException("Incorrect password");
        return true;
    }

    public UserWithoutPassDTO register(RegisterDTO regData) {
        String email= regData.getEmail();
        //fix for different exceptions

        if(!validator.isValidEmail(email)){
            throw new BadRequestException("Incorrect Email");
        }
        if(!validator.isValidNumber(regData.getPhoneNumber())){
            throw new BadRequestException("Incorrect phone number");
        }
        if(!(regData.getPassword().equals( regData.getConfirmPassword()))){
            throw new BadRequestException("Passwords must match");
        }
        if(userRepository.existsByEmail(email)){
            throw new BadRequestException("Email already in use");
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
        u.setPassword(validator.encodePassword(changePassData.getNewPassword()));
        userRepository.save(u);
        return mapper.map(u, UserWithoutPassDTO.class);
    }

    public List<UserWithoutPassDTO> searchUsers(SearchUDTO criteria) {
        //this is when you know what type exactly is the search data(in the json)
        //toDo: make it work like a searchbar, idea: if it's 1 parameter add it in ever field?
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> criteria.getFirstName() == null
                        || user.getFirstName().equalsIgnoreCase(criteria.getFirstName()))
                .filter(user -> criteria.getLastName() == null
                        || user.getLastName().equalsIgnoreCase(criteria.getLastName()))
                .filter(user -> criteria.getEmail() == null
                        || user.getEmail().equalsIgnoreCase(criteria.getEmail()))
                .filter(user -> criteria.getPhoneNumber() == null
                        || user.getPhoneNumber().equalsIgnoreCase(criteria.getPhoneNumber()))
                .map(u->mapper.map(u,UserWithoutPassDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public UserWithoutPassDTO updateUser(UserWithoutPassDTO updateData, int id) {
        Optional<User> optUser= Optional.ofNullable(userRepository.findById(id));
        if (!optUser.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User u = optUser.get();
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
        //u.setIsVerified(updateData.isVerified());
        u.setAdditionalInfo(updateData.getAdditionalInfo());
        u.setGender(updateData.getGender());
        userRepository.save(u);
        return mapper.map(u,UserWithoutPassDTO.class);
    }
    //ne se raboti sys sesii,response ili request

}
