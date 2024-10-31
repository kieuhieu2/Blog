package com.vnua.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vnua.blog.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
