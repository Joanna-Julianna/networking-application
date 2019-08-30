package com.networkingapplication.posting;

import com.networkingapplication.user.UserDto;
import com.networkingapplication.user.UserTestData;

public class PostTestData {

    public static PostDto initPost(String content) {
        UserDto user = UserTestData.initUser();
        PostDto post = new PostDto();
        post.setContent(content);
        post.setAuthor(user);
        return post;
    }


}
