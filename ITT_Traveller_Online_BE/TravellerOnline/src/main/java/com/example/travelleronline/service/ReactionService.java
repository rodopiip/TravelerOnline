package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.reaction.ReactionDTO;
import com.example.travelleronline.model.entities.Reaction;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.repositories.CommentRepository;
import com.example.travelleronline.model.repositories.ReactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
@Service
public class ReactionService extends AbstractService{
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    CommentRepository commentRepository;
    @Transactional
    public ReactionDTO reactToPost(ReactionDTO reactionDTO,int userId, int postId) {
        Reaction react=null;
        boolean existed=false;
        Optional<Reaction> opt=reactionRepository.getByUserAndPost(userId,postId);
        if(opt.isPresent()){
            react=opt.get();
            existed=true;
            if(react.getStatus()== reactionDTO.getStatus()){
                throw new BadRequestException("The reaction is the same as it was before, you cannot double like");
            }
        }else {
            react=Reaction.builder()
                    .post(postRepository.getById(postId))
                    .user(userRepository.getById(userId))
                    .build();
        }
        react.setStatus(reactionDTO.getStatus());
        reactionRepository.save(react);
        int newRating;
        if(existed){
            newRating= react.getPost().getRating()+(react.getStatus()*2);
        }else {
            newRating= react.getPost().getRating()+react.getStatus();
        }
        react.getPost().setRating(newRating);
        postRepository.save(react.getPost());
        return null;
    }
    @Transactional
    public ReactionDTO reactToComment(ReactionDTO reactionDTO,int userId, int commentId) {
        Reaction react=null;
        boolean existed=false;
        Optional<Reaction> opt=reactionRepository.getByUserAndComment(userId,commentId);
        if(opt.isPresent()){
            react=opt.get();
            existed=true;
            if(react.getStatus() == reactionDTO.getStatus()){
                throw new BadRequestException("The reaction is the same as it was before, you cannot double like");
            }
        }else {
            react=Reaction.builder()
                    .comment(commentRepository.getById(commentId))
                    .user(userRepository.getById(userId))
                    .build();
        }
        react.setStatus(reactionDTO.getStatus());
        react=reactionRepository.save(react);
        int newRating;
        if(existed){
            newRating= react.getComment().getRating()+(react.getStatus()*2);
        }else {
            newRating= react.getComment().getRating()+react.getStatus();
        }
        react.getComment().setRating(newRating);
        commentRepository.save(react.getComment());
        return reactionDTO;
    }
}
