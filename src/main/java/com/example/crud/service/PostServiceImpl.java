package com.example.crud.service;

import com.example.crud.entity.Post;
import com.example.crud.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @Override
  public Post create(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Optional<Post> read(long id) {
    return postRepository.findById(id);
  }

  @Override
  public Post update(Post post) {
    Post existingPost = postRepository.findById(post.getId())
      .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다: " + post.getId()));

    existingPost.setTitle(post.getTitle());
    existingPost.setContent(post.getContent());
    existingPost.setAuthor(post.getAuthor());

    return postRepository.save(existingPost);
  }

  @Override
  public void delete(long id) {
    if (!postRepository.existsById(id)) {
      throw new EntityNotFoundException("해당 ID의 게시글이 존재하지 않습니다: " + id);
    }
    postRepository.deleteById(id);
  }
}
