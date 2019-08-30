package com.networkingapplication.following;

import com.networkingapplication.user.User;
import com.networkingapplication.user.UserFollowingDto;
import com.networkingapplication.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class FollowingService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    Optional<UserFollowingDto> follow(Long followerId, Long followingUserId) {
        Optional<User> follower = userRepository.findById(followerId);
        if (follower.isEmpty()) {
            return Optional.empty();
        }

        Optional<User> followingUser = userRepository.findById(followingUserId);
        if (followingUser.isEmpty()) {
            return Optional.of(modelMapper.map(follower, UserFollowingDto.class));
        }

        boolean followed = follow(follower.get(), followingUser.get());
        if (followed) {
            User user = userRepository.save(follower.get());
            return Optional.of(modelMapper.map(user, UserFollowingDto.class));
        }

        return Optional.empty();
    }

    private boolean follow(User follower, User newFollowing) {
        if (!follower.getFollowing().contains(newFollowing.getId())) {
            follower.getFollowing().add(newFollowing);
            return true;
        }
        return false;
    }
}
