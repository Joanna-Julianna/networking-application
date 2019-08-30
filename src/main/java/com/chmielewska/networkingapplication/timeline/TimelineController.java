package com.chmielewska.networkingapplication.timeline;

import com.chmielewska.networkingapplication.posting.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("timeline")
@AllArgsConstructor
public class TimelineController {

    private final TimelineService timelineService;

    @GetMapping("findFollowingUsersPosts/{userId}")
    public ResponseEntity<List<PostDto>> findFollowingUsersPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(timelineService.findFollowingUsersPosts(userId));
    }
}
