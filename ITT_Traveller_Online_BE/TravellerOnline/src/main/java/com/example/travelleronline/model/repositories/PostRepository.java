package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    //query derivation from method names
    public Optional<Post> findById(int id);
//    public List<Post> getAll();//todo add criteria + Pageable
    public List<Post> findByOwnerId(int ownerId);

    Page<Post> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM posts WHERE user_id IN (:userIds) ORDER BY date_created DESC", nativeQuery = true)
    Page<Post> newsFeedByDate(@Param("userIds") List<Integer> userIds, Pageable pageable);

    @Query(value = "SELECT * FROM posts WHERE user_id IN (:userIds) ORDER BY rating DESC", nativeQuery = true)
    Page<Post> newsFeedByLikes(@Param("userIds") List<Integer> userIds, Pageable pageable);
}
