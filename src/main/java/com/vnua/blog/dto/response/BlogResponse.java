package com.vnua.blog.dto.response;

import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogResponse implements Serializable {
    String title;
    @Lob
    String description;
    @Lob
    String content;
    Timestamp createdAt;
    Timestamp updatedAt;
    String username;
    byte[] image;
}
