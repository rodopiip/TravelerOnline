package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
    Optional<Post> findById(int id);
}
