package com.chmielewska.networkingapplication.posting;

import com.chmielewska.networkingapplication.user.UserDto;
import com.chmielewska.networkingapplication.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    PostDto addPost(PostDto post) {
        UserDto author = userService.registerUser(post.getAuthor());
        post.setAuthor(author);
        post.setCreationDate(LocalDateTime.now());
        Post postEntity = postRepository.save(modelMapper.map(post, Post.class));

        return modelMapper.map(postEntity, PostDto.class);
    }

    List<PostDto> findUserPosts(Long userId) {
        List<Post> posts = postRepository.findByAuthorIdOrderByCreationDateDesc(userId);
        return modelMapper.map(posts, new TypeToken<List<PostDto>>() {}.getType());
    }

}
