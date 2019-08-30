package com.chmielewska.networkingapplication.user;

import lombok.Data;

import java.util.List;

@Data
public class UserFollowingDto {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private List<UserDto> following;
}
