package com.example.crud.service;

import com.example.crud.entity.Post;

import java.util.Optional;

public interface PostService {
  Post create(Post post); // 생성된 객체 반환
  Optional<Post> read(long id); // Optional로 감싸서 안전하게 반환
  Post update(Post post); // 업데이트된 객체 반환
  void delete(long id); // 성공 여부 반환
}
