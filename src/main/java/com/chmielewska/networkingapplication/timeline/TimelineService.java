package com.chmielewska.networkingapplication.timeline;

import com.chmielewska.networkingapplication.posting.PostDto;
import com.chmielewska.networkingapplication.posting.PostRepository;
import com.chmielewska.networkingapplication.user.User;
import com.chmielewska.networkingapplication.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class TimelineService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    List<PostDto> findFollowingUsersPosts(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Set<Long> followingIds = user.get().getFollowing().stream()
                    .map(User::getId)
                    .collect(Collectors.toSet());
            return modelMapper.map(postRepository.findByAuthorIdOrderByCreationDateDesc(followingIds), new TypeToken<List<PostDto>>() {}.getType());
        }
        return new ArrayList<>();
    }

}
