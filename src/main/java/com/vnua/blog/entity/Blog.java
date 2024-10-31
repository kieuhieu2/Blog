package com.vnua.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String description;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String content;
    Timestamp createdAt;
    Timestamp updatedAt;
    String imagePath;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    byte[] image;

    @ManyToOne
    @JoinColumn(name = "username")
    private User author;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
