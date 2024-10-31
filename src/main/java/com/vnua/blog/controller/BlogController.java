package com.vnua.blog.controller;

import com.vnua.blog.dto.request.ApiResponse;
import com.vnua.blog.dto.request.BlogCreationRequest;
import com.vnua.blog.dto.response.BlogDescriptionResponse;
import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.dto.response.BlogSummaryResponse;
import com.vnua.blog.dto.response.getBlogResponse;
import com.vnua.blog.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController {
    BlogService blogService;

    @PostMapping
    ApiResponse<BlogResponse> createBlog(@ModelAttribute BlogCreationRequest request) throws IOException {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.createBlog(request))
                .build();
    }

    @PutMapping("/{title}")
    ApiResponse<BlogResponse> updateBlog(@PathVariable String title, @ModelAttribute BlogCreationRequest request) throws IOException {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.updateBlog(title, request))
                .build();
    }

    @DeleteMapping("/{title}")
    ApiResponse<String> deleteBlog(@PathVariable String title) {
        return ApiResponse.<String>builder()
                .result(blogService.deleteBlog(title))
                .build();
    }

    @GetMapping
    ApiResponse<List<BlogResponse>> getBlog() {
        return ApiResponse.<List<BlogResponse>>builder()
                .result(blogService.getBlog())
                .build();
    }

    @GetMapping("/{title}")
    ApiResponse<List<BlogResponse>> getBlog(@PathVariable String title) {
        return ApiResponse.<List<BlogResponse>>builder()
                .result(blogService.findBlog(title))
                .build();
    }

    @GetMapping("/getMyBlog")
    public List<BlogSummaryResponse> getMyBlogs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return blogService.getBlogsByUsername(username);
    }
}
