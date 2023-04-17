package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.reaction.ReactionDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class ReactionService extends AbstractService{
    public ReactionDTO react(ReactionDTO reactionDTO, HttpSession session) {
        int userId = getUserId(session);
        return null;
    }
}
