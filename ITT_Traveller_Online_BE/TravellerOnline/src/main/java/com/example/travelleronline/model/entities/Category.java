package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> posts;
}
