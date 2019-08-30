package com.chmielewska.networkingapplication.following;

import com.chmielewska.networkingapplication.user.User;
import com.chmielewska.networkingapplication.user.UserFollowingDto;
import com.chmielewska.networkingapplication.user.UserRepository;
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

        boolean followed = follower.get().follow(followingUser.get());
        if (followed) {
            User user = userRepository.save(follower.get());
            return Optional.of(modelMapper.map(user, UserFollowingDto.class));
        }

        return Optional.empty();
    }
}
