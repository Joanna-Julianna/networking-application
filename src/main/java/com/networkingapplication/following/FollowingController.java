package com.networkingapplication.following;

import com.networkingapplication.user.UserFollowingDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class FollowingController {

    private final FollowingService followingService;

    @PostMapping("follow/{followerId}/{followingId}")
    public ResponseEntity follow(@PathVariable Long followerId, @PathVariable Long followingId) {
        Optional<UserFollowingDto> user = followingService.follow(followerId, followingId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
