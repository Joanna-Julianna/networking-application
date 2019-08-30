package com.networkingapplication.posting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void addPost() {
        //GIVEN
        PostDto post = PostTestData.initPost("content");
        HttpEntity<PostDto> entity = new HttpEntity<>(post, headers);

        //WHEN
        ResponseEntity<PostDto> response = restTemplate.exchange(
                createURLWithPort("/post/add"),
                HttpMethod.POST, entity, PostDto.class);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(post.getContent(), response.getBody().getContent());
        assertNotNull(response.getBody().getCreationDate());
    }

    @Test
    public void tryAddPostWith141Characters() {
        //GIVEN
        String content = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum " +
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ip ";
        PostDto post = PostTestData.initPost(content);
        HttpEntity<PostDto> entity = new HttpEntity<>(post, headers);

        //WHEN
        ResponseEntity<PostDto> response = restTemplate.exchange(
                createURLWithPort("/post/add"),
                HttpMethod.POST, entity, PostDto.class);

        //THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @Sql({"/users.sql", "/posts.sql"})
    public void findUserPosts() {
        //GIVEN
        Long userId = 1L;

        //WHEN
        ResponseEntity<List<PostDto>> response = restTemplate.exchange(
                createURLWithPort("/post/findUserPosts/" + userId),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}