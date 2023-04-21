package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    //query derivation from method names
    public Optional<Post> findById(int id);
//    public List<Post> getAll();//todo add criteria + Pageable
    public List<Post> findByOwnerId(int ownerId);
}
