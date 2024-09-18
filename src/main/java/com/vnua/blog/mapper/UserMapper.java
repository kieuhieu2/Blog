package com.vnua.blog.mapper;

import com.vnua.blog.dto.request.UserCreationRequest;
import com.vnua.blog.dto.request.UserUpdateRequest;
import com.vnua.blog.dto.response.UserResponse;
import com.vnua.blog.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

