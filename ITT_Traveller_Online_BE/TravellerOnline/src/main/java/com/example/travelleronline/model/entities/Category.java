package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "keywords")
    private String keywords;

    //getters and setters
}
