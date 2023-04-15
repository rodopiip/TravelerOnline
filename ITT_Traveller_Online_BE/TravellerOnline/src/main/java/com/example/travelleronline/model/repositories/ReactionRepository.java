package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
}
