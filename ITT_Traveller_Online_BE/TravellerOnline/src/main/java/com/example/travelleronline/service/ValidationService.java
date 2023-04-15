package com.example.travelleronline.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Maybe make it Singelton insteed of service?
@Service
public class ValidationService {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean IsValidEmail(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }


    public boolean isValidPassword(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }
    public String encodePassword(String password){
        return encoder.encode(password);
    }
}
