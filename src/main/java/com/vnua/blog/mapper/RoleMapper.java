package com.vnua.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vnua.blog.dto.request.RoleRequest;
import com.vnua.blog.dto.response.RoleResponse;
import com.vnua.blog.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
