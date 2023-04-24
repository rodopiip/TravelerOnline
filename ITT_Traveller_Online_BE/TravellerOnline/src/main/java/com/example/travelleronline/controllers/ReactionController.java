package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.reaction.ReactionDTO;
import com.example.travelleronline.service.ReactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Reaction Controller", description = "Reacton endpoints")
@RestController
public class ReactionController extends AbstractController{
    @Autowired
    private ReactionService reactionService;//todo

    @PostMapping("/posts/{postId}/reactions/{status}")
    public ReactionDTO react(@PathVariable("postId") int postId,
                             @PathVariable("status") int status,
                             HttpSession session){
        int userId=0;//todo:
        ReactionDTO reactionDTO = new ReactionDTO(null, userId, status, postId, null);//todo ReactionDTO
        return reactionService.react(reactionDTO,getLoggedId(session));//todo
    }
}
