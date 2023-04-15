package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer> {
}
