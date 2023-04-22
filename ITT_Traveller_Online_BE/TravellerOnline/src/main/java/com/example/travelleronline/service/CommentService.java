package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.comment.CommentDTO;
import com.example.travelleronline.model.DTOs.comment.ContentDTO;
import com.example.travelleronline.model.entities.Comment;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.BadSaveToDBException;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.model.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService extends AbstractService{

    @Autowired
    private CommentRepository commentRepository;

    public CommentDTO getCommentWithChildComments(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
        return mapCommentWithReplies(comment);
    }

    private CommentDTO mapCommentWithReplies(Comment comment) {
        CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
        commentDTO.setParentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null);

        Set<Comment> childComments = commentRepository.findAllByParentCommentId(comment.getId());
        Set<CommentDTO> childCommentDTOs = childComments.stream()
                .map(this::mapCommentWithReplies)
                .collect(Collectors.toSet());

        commentDTO.setChildComments(childCommentDTOs);
        return commentDTO;
    }

    public ContentDTO saveByPost(ContentDTO contentData, int postId,int userId) {

        try {
            Comment comment = Comment.builder()
                    .user(userRepository.findById(userId).get())
                    .content(contentData.getContent())
                    .dateAdded(LocalDateTime.now())
                    .rating(0)
                    .post(postRepository.findById(postId).orElseThrow(() ->new BadRequestException("No such post exists")))
                    //.parentComment(null)
                    .build();
            System.out.println(comment);
            commentRepository.save(comment);
            return mapper.map(comment,ContentDTO.class);
        }catch (ConstraintViolationException e){
            throw new BadRequestException("Comments can be between 1 and 300 symbols");
        }
    }
    public ContentDTO saveByComment(ContentDTO contentData, int commentedCommentId,int userId) {

        try {
            Comment comment = Comment.builder()
                    .user(userRepository.findById(userId).get())
                    .content(contentData.getContent())
                    .parentComment(commentRepository.findById(commentedCommentId).
                            orElseThrow(()-> new BadRequestException("no such comment exists")))
//                    .post(null)
                    .dateAdded(LocalDateTime.now())
                    .rating(0)
                    .build();
            System.out.println(comment);
            return mapper.map(commentRepository.save(comment),ContentDTO.class);
        }catch (ConstraintViolationException e){
            throw new BadRequestException("Comments can be between 1 and 300 symbols");
        }
    }
    @Transactional
    public ContentDTO deleteById(int id,int userId) {
        Comment toBeDeleted=commentRepository.findById(id).orElseThrow(()-> new BadRequestException("Does not exist"));
        if(userId==toBeDeleted.getUser().getId()){
            commentRepository.delete(toBeDeleted);
            return mapper.map(toBeDeleted, ContentDTO.class);
        }else{
            throw new UnauthorizedException("You are not the creator of this comment");
        }
    }
    public List<CommentDTO> getAllPostComments(int postId) {
        return commentRepository.findAllByPostId(postId)
                .stream()
                .map(comment -> mapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getAllCommentOfUser(int userId){
        if(!userRepository.existsById(userId)) throw new BadRequestException("User does not exist");
        return commentRepository.findAllByUserId(userId)
                .stream()
                .map(comment -> mapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public ContentDTO edit(int commentId, ContentDTO contentData, int loggedId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(()->new BadRequestException("No such comment"));
        if(loggedId==existingComment.getUser().getId()){
            existingComment.setContent(contentData.getContent());
        }else {
            throw new UnauthorizedException("You are not the owner of this comment.");
        }
        try{
            return mapper.map(commentRepository.save(existingComment), ContentDTO.class);
        }catch (ConstraintViolationException e){
            throw new BadRequestException("Comments can be between 1 and 300 symbols");
        }
    }

}
