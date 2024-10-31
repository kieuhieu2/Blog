package com.vnua.blog.mapper;

import com.vnua.blog.dto.request.BlogCreationRequest;
import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    @Mapping(target = "image", source = "image", qualifiedByName = "multipartFileToByteArray")
    @Mapping(target = "imagePath", ignore = true)
    Blog toBlog(BlogCreationRequest request);

    BlogResponse toBlogResponse(Blog blog);

    @Named("multipartFileToByteArray")
    default byte[] multipartFileToByteArray(MultipartFile file) throws IOException {
        if (file != null) {
            return file.getBytes();
        }
        return new byte[0]; // or handle null case appropriately
    }
}


