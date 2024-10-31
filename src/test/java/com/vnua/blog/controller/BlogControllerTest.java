package com.vnua.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnua.blog.dto.request.BlogCreationRequest;
import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.service.BlogService;
import com.vnua.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class BlogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private BlogService blogService;

    private BlogCreationRequest request;
    private BlogResponse blogResponse;
    @BeforeEach
    public void setUp() {
        request = new BlogCreationRequest();
        request.setTitle("Test Title");
        request.setDescription("Test Description");
        request.setContent("Test Content");
        request.setImage(null);

        blogResponse = BlogResponse.builder()
                .title("Test Title")
                .description("Test Description")
                .content("Test Content")
                .build();
    }

    @Test
    public void testCreateBlog() throws Exception {
        when(blogService.createBlog(any(BlogCreationRequest.class))).thenReturn(blogResponse);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(blogResponse)));
    }

}
