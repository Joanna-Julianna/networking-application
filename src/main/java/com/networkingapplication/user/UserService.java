package com.networkingapplication.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Register user only if not exist user with given name
     *
     * @param userDto user data
     * @return registered user
     */
    public UserDto registerUser(UserDto userDto) {
        Optional<User> foundUser = userRepository.findByUserName(userDto.getUserName());
        User registeredUser = foundUser.orElseGet(() -> saveUser(userDto));
        return modelMapper.map(registeredUser, UserDto.class);
    }

    private User saveUser(UserDto userDto) {
        return userRepository.save(modelMapper.map(userDto, User.class));
    }
}
