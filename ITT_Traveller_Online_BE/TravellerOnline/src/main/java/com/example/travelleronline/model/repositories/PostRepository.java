package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    /*
    mind-blowing: thanks to Spring Data JPA:
    method implementation based on method names:
    runtime query derivation from method names
     */

}
