package com.networkingapplication.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    private String firstName;

    private String lastName;

    @ManyToMany
    private Set<User> following;

}
