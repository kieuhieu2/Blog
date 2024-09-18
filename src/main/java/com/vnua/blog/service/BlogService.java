package com.vnua.blog.service;

import com.vnua.blog.dto.request.BlogCreationRequest;
import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.entity.Blog;
import com.vnua.blog.exception.AppException;
import com.vnua.blog.exception.ErrorCode;
import com.vnua.blog.mapper.BlogMapper;
import com.vnua.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.mapstruct.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogService {
    BlogRepository blogRepository;
    BlogMapper blogMapper;

    public BlogResponse createBlog(BlogCreationRequest request) {
        MultipartFile file = request.getImage(); // Get the image from request

        try {
            // Convert MultipartFile to byte[]
            byte[] imageBytes = file.getBytes();

            // Map request to entity
            Blog blog = blogMapper.toBlog(request);
            blog.setImage(imageBytes); // Set byte[] image data
            blog.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            // Save the blog entity
            blogRepository.save(blog);

            // Encode image data as Base64 for response
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            return BlogResponse.builder()
                    .title(blog.getTitle())
                    .content(blog.getContent())
                    .image(base64Image.getBytes()) // Set encoded image
                    .imagePath(blog.getImagePath())
                    .createdAt(blog.getCreatedAt())
                    .updatedAt(blog.getUpdatedAt())
                    .build();

        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file", ex);
        }
    }

    public BlogResponse getBlogResponses(String title) {
        Blog blog = blogRepository.findByTitle(title)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_EXISTED));

        // Encode image data as Base64 for response
        String base64Image = blog.getImage() != null ? Base64.getEncoder().encodeToString(blog.getImage()) : "";

        return BlogResponse.builder()
                .title(blog.getTitle())
                .content(blog.getContent())
                .image(base64Image.getBytes()) // Set encoded image
                .imagePath(blog.getImagePath())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }
}
