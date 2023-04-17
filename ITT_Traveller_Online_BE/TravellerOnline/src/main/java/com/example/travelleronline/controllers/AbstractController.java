package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.ErrorDTO;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AbstractController {
    @ExceptionHandler(BadRequestException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(Exception e){
        return generateErrorDTO(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
   // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(Exception e){
        return generateErrorDTO(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(Exception e){
        return generateErrorDTO(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleRest(Exception e){
        return generateErrorDTO(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDTO generateErrorDTO(Exception e, HttpStatus s){
        return ErrorDTO.builder()
                .msg(e.getMessage())
                .time(LocalDateTime.now())
                .status(s.value())
                .build();
    }

    protected int getLoggedId(HttpSession s){
        // Solid: 1 method has to do 1 thing.
        // this does 2 things.
        //      1)Checks if a user is logged
        //      2)returns the userId
        Optional<Integer> userId=Optional.ofNullable((Integer) s.getAttribute("LOGGED_ID"));
        if(userId.isPresent()){
            return userId.get();
        }
        throw new UnauthorizedException("You have to Login");
    }

    public boolean checkOwner(HttpSession s, int ownerId){
        if(getLoggedId(s)==ownerId) return true;
        else{
            throw new UnauthorizedException("You need to be the owner.");
        }
    }


}
