package com.vnua.blog.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class getBlogResponse {
    String title;
    String content;
    Blob image;
}
