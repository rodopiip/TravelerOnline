package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotBlank
    @Size(max = 500)
     */
    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    @NotEmpty
    @Size(max = 30)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotEmpty
    @Size(max = 500)
    private String description;

    @Column(name = "location")
    @Size(max = 200)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User owner;//todo
    @ManyToOne(fetch = FetchType.LAZY)
    @NotEmpty
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP")
    @NotEmpty
    private LocalDateTime dateCreated;
    @Size(max = 500)
    @Column(name="additional_info")
    private String additionalInfo;
    @Column(name = "rating")
    private Integer rating;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<UserSavePost> savedByUsers = new HashSet();
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Reaction>reactions;
}
