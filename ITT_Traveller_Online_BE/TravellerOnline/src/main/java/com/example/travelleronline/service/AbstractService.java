package com.example.travelleronline.service;

import com.example.travelleronline.model.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class AbstractService {
    @Autowired
    protected ModelMapper mapper;
    public boolean checkOwner(HttpSession s, int ownerId){
        if(getUserId(s)==ownerId) return true;
        else{
            throw new UnauthorizedException("You need to be the owner.");
        }
    }

    public int getUserId(HttpSession s){
        return (Integer) s.getAttribute("LOGGED_ID");
    }
    public boolean isLogged(HttpSession s){
        Optional<Boolean> id=Optional.ofNullable((Boolean) s.getAttribute("LOGGED"));
        if(id.isPresent()){
            return true;
        }
        throw new UnauthorizedException("You have to Login");
    }
}
