package com.example.travelleronline.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    @Size(min = 3,max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 3,max = 50)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    @NotNull
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
    private Set<User> subscribers=new HashSet<>();//todo for subscribers DTO .map(User::getSubscribers)

    @ManyToMany(mappedBy = "subscribers")
    private Set<User> subscribedTo=new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSavePost> savedPosts = new HashSet<>();





}
