package com.example.travelleronline.service;

import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.model.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class SessionService {
    public static boolean checkOwner(HttpSession s, int ownerId){
        if(getUserId(s)==ownerId) return true;
        else{
            throw new UnauthorizedException("You need to be the owner.");
        }
    }

    public static int getUserId(HttpSession s){
        return (Integer) s.getAttribute("LOGGED_ID");
    }
    public static boolean isLogged(HttpSession s){
        Optional<Boolean> id=Optional.ofNullable((Boolean) s.getAttribute("LOGGED"));
        if(id.isPresent()){
            return true;
        }
        throw new UnauthorizedException("You have to Login");
    }
}
