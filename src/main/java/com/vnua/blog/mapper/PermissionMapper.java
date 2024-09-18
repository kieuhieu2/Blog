package com.vnua.blog.mapper;

import org.mapstruct.Mapper;

import com.vnua.blog.dto.request.PermissionRequest;
import com.vnua.blog.dto.response.PermissionResponse;
import com.vnua.blog.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
