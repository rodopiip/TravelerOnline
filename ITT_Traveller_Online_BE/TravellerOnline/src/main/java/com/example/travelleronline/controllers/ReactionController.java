package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.reaction.ReactionDTO;
import com.example.travelleronline.service.ReactionService;
import com.example.travelleronline.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactionController {
    @Autowired
    private ReactionService reactionService;//todo

    //react - localhost:3333/posts/{postId}/reactions/{status}
    @PostMapping("/posts/{postId}/reactions/{status}")
    public ReactionDTO react(@PathVariable("postId") int postId,
                             @PathVariable("status") int status,
                             HttpSession session){
        int userId = SessionService.getUserId(session);//todo s -> session
        ReactionDTO reactionDTO = new ReactionDTO(null, userId, status, postId, null);//todo ReactionDTO
        return reactionService.react(reactionDTO);//todo
    }
}
