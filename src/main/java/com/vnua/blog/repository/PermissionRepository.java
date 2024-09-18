package com.vnua.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vnua.blog.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
