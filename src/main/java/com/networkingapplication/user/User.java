package com.networkingapplication.user;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<User> following = new ArrayList<>();

    public boolean follow(User newFollowing) {
        if (isNewFollowingUser(newFollowing.getId())) {
            following.add(newFollowing);
            return true;
        }
        return false;
    }

    private boolean isNewFollowingUser(Long followingId) {
        return following.stream()
                .noneMatch(previousFollowing -> followingId.equals(previousFollowing.getId()));

    }
}
