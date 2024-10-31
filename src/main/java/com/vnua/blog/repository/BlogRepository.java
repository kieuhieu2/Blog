package com.vnua.blog.repository;

import com.vnua.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByTitle(String title);

    @Query(value = "SELECT b FROM Blog b ORDER BY b.createdAt DESC")
    List<Blog> findTop10ByOrderByCreatedAtDesc();

    List<Blog> findByAuthor_Username(String username);

}
