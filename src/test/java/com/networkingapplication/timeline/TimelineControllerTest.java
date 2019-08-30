package com.networkingapplication.timeline;

import com.networkingapplication.posting.PostDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TimelineControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void findFollowingUsersPostsIfThereIsAnyPost() {
        //GIVEN
        Long userId = 1L;

        //WHEN
        ResponseEntity<List<PostDto>> response = restTemplate.exchange(
                createURLWithPort("/timeline/findFollowingUsersPosts/" + userId),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }


    @Test
    @Sql({"/users.sql", "/following.sql", "/posts.sql"})
    public void findFollowingUsersPosts() {
        //GIVEN
        Long userId = 1L;

        //WHEN
        ResponseEntity<List<PostDto>> response = restTemplate.exchange(
                createURLWithPort("/timeline/findFollowingUsersPosts/" + userId),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}