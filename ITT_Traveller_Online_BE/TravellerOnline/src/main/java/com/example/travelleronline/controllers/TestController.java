package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.ErrorDTO;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.service.ValidationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends AbstractController{
    @Autowired
    private ValidationService validator;
    @GetMapping("/test")
    public ErrorDTO test(HttpSession s){
        System.out.println("Session Attrb:"+s.getAttributeNames());

        String emailValid="Test@abv.bg";
        String emailInValid="aADszaAq!0394";
        System.out.println(emailInValid+" : "+validator.isValidEmail(emailInValid));

        System.out.println(emailValid+" : "+validator.isValidEmail(emailValid));

        String password="151232RFsAsx!";
        String encodedPassword=validator.encodePassword(password);

        System.out.println("Testing:"+password+" to "+encodedPassword+"\n:"+validator.isCorrectPassword(password,encodedPassword));

        throw new UnauthorizedException("Testing Done, Check console.");


    }
}
