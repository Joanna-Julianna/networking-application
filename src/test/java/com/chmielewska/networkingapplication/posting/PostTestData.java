package com.chmielewska.networkingapplication.posting;

import com.chmielewska.networkingapplication.user.UserDto;
import com.chmielewska.networkingapplication.user.UserTestData;

public class PostTestData {

    public static PostDto initPost(String content) {
        UserDto user = UserTestData.initUser();
        PostDto post = new PostDto();
        post.setContent(content);
        post.setAuthor(user);
        return post;
    }


}
