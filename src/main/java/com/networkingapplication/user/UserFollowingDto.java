package com.networkingapplication.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserFollowingDto {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private Set<UserDto> following;
}
