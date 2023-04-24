package com.example.travelleronline.model.repositories;

import com.example.travelleronline.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Optional<Image>> findAllByPost_Id(int id);
}
