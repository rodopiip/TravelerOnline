package com.example.travelleronline.controllers;

import com.example.travelleronline.model.DTOs.reaction.ReactionDTO;
import com.example.travelleronline.service.ReactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
<<<<<<< HEAD
public class ReactionController extends AbstractController{
=======
public class ReactionController extends AbstractController {
>>>>>>> f232377eec16f745e79bb65612b867f2ea4df1fd
    @Autowired
    private ReactionService reactionService;//todo

    //react - localhost:3333/posts/{postId}/reactions/{status}
    @PostMapping("/posts/{postId}/reactions/{status}")
    public ReactionDTO react(@PathVariable("postId") int postId,
                             @PathVariable("status") int status,
                             HttpSession session){
        int userId=0;//todo:
        ReactionDTO reactionDTO = new ReactionDTO(null, userId, status, postId, null);//todo ReactionDTO
        return reactionService.react(reactionDTO,getLoggedId(session));//todo
    }
}
