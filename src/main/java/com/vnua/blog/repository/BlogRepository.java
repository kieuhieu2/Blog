package com.vnua.blog.repository;

import com.vnua.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByTitle(String title);
}
