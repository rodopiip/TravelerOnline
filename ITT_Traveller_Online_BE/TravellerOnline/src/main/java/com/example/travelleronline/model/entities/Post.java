package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "posts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrement
    private Integer id;

    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 30)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    //todo constraint
    private String description;

    @Column(name = "location")
    //todo constraint
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    //todo constraint
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User owner;//todo

    @ManyToOne(fetch = FetchType.LAZY)
    //todo constraint
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(name = "video_url")
    //todo constraint
    private String videoUrl;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP")
    //todo constraint
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)//todo cascade delete
    private List<Image> images;

    //private List<Image> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<UserSavePost> savedByUsers = new HashSet();

}
