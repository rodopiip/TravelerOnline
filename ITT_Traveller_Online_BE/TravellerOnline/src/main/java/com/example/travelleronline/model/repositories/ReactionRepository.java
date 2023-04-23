package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
    public long countAllByPostId(int postId);
    //@Query(value = "SELECT SUM(r.status) FROM reactions r WHERE r.post_id = :post_Id", nativeQuery = true)
    //public long findReactionSumByPostId(@Param("post_id") int postId);
}
