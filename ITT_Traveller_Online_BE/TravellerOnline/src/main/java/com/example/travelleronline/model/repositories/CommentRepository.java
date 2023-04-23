package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Integer>, PagingAndSortingRepository<Comment,Integer> {
    Page<Comment> findAllByUserId(Pageable pageable, int loggedId);

    Page<Comment> findAllByPostId(Pageable pageable, int postId);
    List<Comment> findAllByPostId(int postId);
    List<Comment> findAllByUserId(Integer userId);
    Optional<Comment> findById(Integer id);
    Set<Comment> findAllByParentCommentId(Integer id);
    public int countAllByPostId(int postId);
    public int countAllByParentCommentId(int parentCommentId);
    long countByUserId(int userId);
}
