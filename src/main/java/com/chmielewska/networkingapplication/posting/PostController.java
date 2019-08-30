package com.chmielewska.networkingapplication.posting;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("add")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto post) {
        return ResponseEntity.ok(postService.addPost(post));
    }

    @GetMapping("findUserPosts/{userId}")
    public ResponseEntity<List<PostDto>> findUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.findUserPosts(userId));
    }
}
