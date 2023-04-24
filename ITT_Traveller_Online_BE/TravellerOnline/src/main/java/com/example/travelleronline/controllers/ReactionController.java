package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.reaction.ReactionDTO;
import com.example.travelleronline.service.ReactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Reaction Controller", description = "Reacton endpoints")
@RestController
public class ReactionController extends AbstractController{
    @Autowired
    private ReactionService reactionService;
    @PostMapping("/posts/{postId}/reaction")
    public ReactionDTO reactToPost(@PathVariable("postId") int postId,
                             @RequestBody() ReactionDTO react,
                             HttpSession session){
        return reactionService.reactToPost(react,getLoggedId(session),postId);//todo
    }
    @PostMapping("/comment/{commentId}/reaction")
    public ReactionDTO reactToComment(@PathVariable("commentId") int commentId,
                             @RequestBody() ReactionDTO react,
                             HttpSession session){
        return reactionService.reactToComment(react,getLoggedId(session),commentId);
    }
}