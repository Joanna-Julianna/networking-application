package com.networkingapplication.following;

import com.networkingapplication.user.UserFollowingDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FollowingControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    @Sql("/users.sql")
    public void follow() {
        //GIVEN
        Long followerId = 1L;
        Long followingId = 2L;
        HttpEntity<Long> entity = new HttpEntity<>(followingId, headers);

        //WHEN
        ResponseEntity<UserFollowingDto> response = restTemplate.exchange(
                createURLWithPort("/user/follow/" + followerId),
                HttpMethod.POST, entity, UserFollowingDto.class);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getFollowing().size());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}