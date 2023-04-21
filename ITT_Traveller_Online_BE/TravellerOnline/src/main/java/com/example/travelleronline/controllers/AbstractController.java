package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.ErrorDTO;

import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AbstractController {
    protected static final Logger logger = LogManager.getLogger(AbstractController.class);

    @ExceptionHandler(BadRequestException.class)
    public ErrorDTO handleBadRequest(Exception e){
        return generateErrorDTO(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ErrorDTO handleUnauthorized(Exception e){
        return generateErrorDTO(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorDTO handleNotFound(Exception e){
        return generateErrorDTO(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ErrorDTO handleSizeLimitExceededException(Exception e) {
        return generateErrorDTO(e, HttpStatus.BAD_REQUEST);
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
        Optional<Integer> userId=Optional.ofNullable((Integer) s.getAttribute("LOGGED_ID"));
        if(userId.isPresent() && userId.get()!=0){
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
