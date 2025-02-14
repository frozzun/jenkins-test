package com.example.crud.dto;

import com.example.crud.entity.Post;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostDto {
  private String title;
  private String content;
  private String author;

  // Entity → DTO 변환용 생성자
  public PostDto(Post post) {
    this.title = post.getTitle();
    this.content = post.getContent();
    this.author = post.getAuthor();
  }
}

