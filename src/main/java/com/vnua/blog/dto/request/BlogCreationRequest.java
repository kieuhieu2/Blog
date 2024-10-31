package com.vnua.blog.dto.request;

import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogCreationRequest implements Serializable {
    String title;
    @Lob
    String description;
    @Lob
    String content;
    MultipartFile image;


}
