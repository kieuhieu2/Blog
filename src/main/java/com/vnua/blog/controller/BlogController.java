package com.vnua.blog.controller;

import com.vnua.blog.dto.request.ApiResponse;
import com.vnua.blog.dto.request.BlogCreationRequest;
import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.dto.response.getBlogResponse;
import com.vnua.blog.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController {
    BlogService blogService;

    @PostMapping
    ApiResponse<BlogResponse> createBlog(@ModelAttribute BlogCreationRequest request) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.createBlog(request))
                .build();
    }

    @GetMapping("/{title}")
    ApiResponse<BlogResponse> getBlog(@PathVariable String title) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.getBlogResponses(title))
                .build();
    }
}
