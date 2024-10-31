package com.vnua.blog.service;

import com.vnua.blog.dto.request.BlogCreationRequest;
import com.vnua.blog.dto.response.BlogDescriptionResponse;
import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.dto.response.BlogSummaryResponse;
import com.vnua.blog.entity.Blog;
import com.vnua.blog.entity.User;
import com.vnua.blog.exception.AppException;
import com.vnua.blog.exception.ErrorCode;
import com.vnua.blog.repository.BlogRepository;
import com.vnua.blog.util.ImageUtils;
import com.vnua.blog.util.tokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogService {

    BlogRepository blogRepository;
    tokenUtils tokenUtils;

    //@Value("${uploadDir}")
    //String uploadDir;

    public BlogResponse createBlog(BlogCreationRequest request) throws IOException {
        String uploadDir = "/home/kieuhieu2/Desktop/blog/ImageBlog";
        String filePath = uploadDir + "/" + request.getImage().getOriginalFilename();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = authentication.getName();
        User author = tokenUtils.getUser(usernameFromToken);

        if (author == null) {
            throw new IllegalArgumentException("Author not found for the provided token.");
        }

        Blog blog = Blog.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .image(ImageUtils.compressImage(request.getImage().getBytes()))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .imagePath(filePath)
                .author(author)
                .build();

        Blog savedBlog = blogRepository.save(blog);

        try {
            request.getImage().transferTo(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Failed to save the image file", e);
        }

        return BlogResponse.builder()
                .title(savedBlog.getTitle())
                .description(savedBlog.getDescription())
                .content(savedBlog.getContent())
                .username(savedBlog.getAuthor().getUsername())
                .updatedAt(savedBlog.getUpdatedAt())
                .updatedAt(savedBlog.getUpdatedAt())
                .image(savedBlog.getImage())
                .build();
    }

    public BlogResponse updateBlog(String title, BlogCreationRequest request) throws IOException {
        String uploadDir = "/home/kieuhieu2/Desktop/blog/ImageBlog";
        String filePath = uploadDir + "/" + request.getImage().getOriginalFilename();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = authentication.getName();
        User author = tokenUtils.getUser(usernameFromToken);

        if (author == null) {
            throw new IllegalArgumentException("Author not found for the provided token.");
        }

        Blog blog = blogRepository.findByTitle(title).stream()
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_EXISTED));

        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setImage(ImageUtils.compressImage(request.getImage().getBytes()));
        blog.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        blog.setImagePath(filePath);
        blog.setAuthor(author);

        Blog updateBlog = blogRepository.save(blog);

        try {
            request.getImage().transferTo(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Failed to save the image file", e);
        }

        return BlogResponse.builder()
                .title(updateBlog.getTitle())
                .description(updateBlog.getDescription())
                .content(updateBlog.getContent())
                .username(updateBlog.getAuthor().getUsername())
                .updatedAt(updateBlog.getUpdatedAt())
                .updatedAt(updateBlog.getUpdatedAt())
                .image(updateBlog.getImage())
                .build();
    }

    public String deleteBlog(String title) {
        Blog blog = blogRepository.findByTitle(title).stream()
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_EXISTED));

        blogRepository.delete(blog);

        return "Delete blog successfully";
    }

    public List<BlogResponse> getBlog() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> BlogResponse.builder()
                        .title(blog.getTitle())
                        .description(blog.getDescription())
                        .updatedAt(blog.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BlogResponse> findBlog(String title) {

        List<Blog> blogs = blogRepository.findByTitle(title);

        if (blogs.isEmpty()) {
            throw new AppException(ErrorCode.BLOG_NOT_EXISTED);
        }

        return blogs.stream()
                .map(blog -> BlogResponse.builder()
                        .title(blog.getTitle())
                        .description(blog.getDescription())
                        .content(blog.getContent())
                        .image(blog.getImage())
                        .createdAt(blog.getCreatedAt())
                        .updatedAt(blog.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BlogSummaryResponse> getBlogsByUsername(String username) {
        List<Blog> blogs = blogRepository.findByAuthor_Username(username);
        return blogs.stream()
                .map(blog -> BlogSummaryResponse.builder()
                        .title(blog.getTitle())
                        .description(blog.getDescription())
                        .updatedAt(blog.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
