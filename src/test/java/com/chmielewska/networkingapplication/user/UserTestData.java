package com.chmielewska.networkingapplication.user;

public class UserTestData {

    public static UserDto initUser() {
        UserDto user = new UserDto();
        user.setUserName("AliceDoe");
        user.setFirstName("Alice");
        user.setLastName("Doe");

        return user;
    }
}
