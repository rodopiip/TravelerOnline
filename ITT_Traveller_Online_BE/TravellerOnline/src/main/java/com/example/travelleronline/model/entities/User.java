package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import lombok.*;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate date_of_birth;

    @Column(name = "profile_photo")
    private String profile_photo;

    @Column(name = "is_verified")
    private Boolean is_verified;

    @Column(name = "additional_info")
    private String additional_info;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_added", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateAdded= LocalDateTime.now();
    //private LocalDateTime dateAdded;

    @Column(name = "verification_code")
    private String verification_code;



    /*
    //owning side
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    //owning side
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    */
    //don't forget subscribers
    /*
     @ManyToMany
    @JoinTable(
            name = "users_subscribed_to_users",
            joinColumns = @JoinColumn(name = "subscribed_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<User> subscribers = new HashSet<>();
     */
    /*
    @ManyToMany(mappedBy = "subscribers")
    private Set<User> subscribedTo = new HashSet<>();
     */
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
     */
}
