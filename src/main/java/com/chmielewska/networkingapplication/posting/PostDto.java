package com.chmielewska.networkingapplication.posting;

import com.chmielewska.networkingapplication.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;

    private String content;

    private UserDto author;

    private LocalDateTime creationDate;
}
