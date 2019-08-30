package com.networkingapplication.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void followUnfollowed() {
        //GIVEN
        User follower = initUser(1L);
        User following = initUser(2L);

        //WHEN
        boolean followed = follower.follow(following);

        //THEN
        assertTrue(followed);
    }

    @Test
    public void tryFollowFollowed() {
        //GIVEN
        User follower = initUser(1L);
        User following = initUser(2L);
        List<User> followingList = new ArrayList<>();
        followingList.add(following);
        follower.setFollowing(followingList);

        //WHEN
        boolean followed = follower.follow(following);

        //THEN
        assertFalse(followed);
    }

    @Test
    public void followUserForUserWithAnotherFollowing() {
        //GIVEN
        User follower = initUser(1L);
        User following = initUser(2L);
        List<User> followingList = new ArrayList<>();
        followingList.add(initUser(3L));
        follower.setFollowing(followingList);

        //WHEN
        boolean followed = follower.follow(following);

        //THEN
        assertTrue(followed);
    }

    private User initUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}