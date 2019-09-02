package com.networkingapplication.following;

import com.networkingapplication.user.UserFollowingDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class FollowingController {

    private final FollowingService followingService;

    @PostMapping("follow/{followerId}")
    public ResponseEntity follow(@PathVariable Long followerId, @RequestBody Long followingId) {
        Optional<UserFollowingDto> user = followingService.follow(followerId, followingId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
