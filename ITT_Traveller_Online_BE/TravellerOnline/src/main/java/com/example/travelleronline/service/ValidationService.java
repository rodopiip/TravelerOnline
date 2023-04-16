package com.example.travelleronline.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Maybe make it Singelton insteed of service?
@Service
public class ValidationService {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String PHONE_REGEX = "^\\+359\\s(?:2[0-9]|3[2-9]|[4-9][2-9])\\-\\d{3}\\-\\d{4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);


    public boolean isValidEmail(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public boolean isValidNumber(String phone){
        Matcher matcher = PHONE_PATTERN.matcher(phone);
        return matcher.matches();
    }
    public boolean isCorrectPassword(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }
    public String encodePassword(String password){
        return encoder.encode(password);
    }



}
