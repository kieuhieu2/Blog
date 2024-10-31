package com.vnua.blog.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogDescriptionResponse {
    String title;
    String description;
    String author;
    Timestamp updatedAt;

}
