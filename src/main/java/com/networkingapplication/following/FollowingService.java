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
            return Optional.empty();
        }

        boolean followed = isUnfollowed(follower.get(), followingUser.get());
        if (followed) {
            follower.get().getFollowing().add(followingUser.get());
            User user = userRepository.save(follower.get());
            return Optional.of(modelMapper.map(user, UserFollowingDto.class));
        }

        return Optional.empty();
    }

    private boolean isUnfollowed(User follower, User newFollowing) {
        return !follower.getFollowing().contains(newFollowing);
    }
}
