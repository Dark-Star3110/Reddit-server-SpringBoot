package edu.vn.redditspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.vn.redditspring.model.Post;
import edu.vn.redditspring.model.PostValidator;
import edu.vn.redditspring.repository.PostRepository;

@Service
public class PostService {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private PostValidator validator;

  public Post getPost(Long id) {
    return postRepository.findById(id).isPresent() ? postRepository.findById(id).get() : null;
  }

  public List<Post> getAllPosts(Integer limit) {
    return Optional.ofNullable(limit).map(value -> postRepository.findAll(PageRequest.of(0, value)).getContent())
        .orElseGet(() -> postRepository.findAll());
  }

  public Post addPost(Post post) {
    if (validator.isValidPost(post)) {
      return postRepository.save(post);
    }
    return null;
  }

  public Post updatePost(Post post) {
    if (validator.isValidPost(post)) {
      postRepository.save(post);
    }
    return null;
  }

  public boolean deletePost(Long id) {
    try {
      postRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
