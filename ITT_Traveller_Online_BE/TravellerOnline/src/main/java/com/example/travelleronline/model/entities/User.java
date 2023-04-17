package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_added", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateAdded= LocalDateTime.now();
    //private LocalDateTime dateAdded;

    @Column(name = "verification_code")
    private String verificationCode;

    @ManyToMany
    @JoinTable(
            name = "users_subscribe_to_users",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name="subscribed_to_id")
    )
    private Set<User> subscribers=new HashSet<>();

    @ManyToMany(mappedBy = "subscribers")
    private Set<User> subscribedTo=new HashSet<>();





    //list<Post>//created posts
    //DILEMA
    @OneToMany(mappedBy = "owner")//map list by exact column name in db
    private List<Post> posts;
    //list<Post>//saved posts
    //list<UserWithoutPasswordDTO>//subscribedToList
    //list<UserWithoutPasswordDTO>//subscribersList



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
