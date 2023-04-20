package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    /*
    JpaRepository<<object_type>, <object_id>>
    Object Id is defined for consequent mapping to to
    a get() method from repository
    mind-blowing: thanks to Spring Data JPA:
    method implementation based on method names:
    runtime query derivation from method names
     */
    public Optional<Post> findById(int id);
    public List<Post> getAll();//todo add criteria
    public List<Post> findByOwnerId(int ownerId);
    public void deleteById(int postId);
}
