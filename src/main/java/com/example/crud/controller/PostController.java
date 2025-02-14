package com.example.crud.controller;

import com.example.crud.dto.PostDto;
import com.example.crud.entity.Post;
import com.example.crud.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  // 📌 1️⃣ [CREATE] 게시글 생성
  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    Post post = Post.builder()
      .title(postDto.getTitle())
      .content(postDto.getContent())
      .author(postDto.getAuthor())
      .build();
    Post savedPost = postService.create(post);
    return ResponseEntity.ok(new PostDto(savedPost));
  }

  // 📌 2️⃣ [READ] 게시글 조회
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
    Optional<Post> post = postService.read(id);
    return post.map(value -> ResponseEntity.ok(new PostDto(value)))
      .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + id));
  }

  // 📌 3️⃣ [UPDATE] 게시글 수정
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
    Post post = Post.builder()
      .id(id)
      .title(postDto.getTitle())
      .content(postDto.getContent())
      .author(postDto.getAuthor())
      .build();
    Post updatedPost = postService.update(post);
    return ResponseEntity.ok(new PostDto(updatedPost));
  }

  // 📌 4️⃣ [DELETE] 게시글 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/test/re/{id}")
  public ResponseEntity<PostDto> testCD(@PathVariable Long id) {
    Optional<Post> post = postService.read(id);
    return post.map(value -> ResponseEntity.ok(new PostDto(value)))
      .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + id));
  }
}
