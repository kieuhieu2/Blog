package com.vnua.blog.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogResponse {
    String title;
    String content;
    Timestamp createdAt;
    Timestamp updatedAt;
    String imagePath;
    byte[] image;
}
