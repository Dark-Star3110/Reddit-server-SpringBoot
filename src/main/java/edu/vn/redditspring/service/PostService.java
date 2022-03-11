package edu.vn.redditspring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.vn.redditspring.model.Post;
import edu.vn.redditspring.model.PostValidator;
import edu.vn.redditspring.payload.PostRequest;
import edu.vn.redditspring.payload.PostResponse;
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

  public PostResponse getAllPosts(PostRequest postRequest) {
    return Optional.ofNullable(postRequest.getTitle())
        .map(value -> new PostResponse(postRepository.countAllByTitleLike(value),
            postRepository.findAllByTitleLike(value,
                PageRequest.of(postRequest.getCurrentPage(), postRequest.getLimit()))))
        .orElseGet(() -> new PostResponse(postRepository.count(),
            postRepository.findAll(PageRequest.of(postRequest.getCurrentPage(), postRequest.getLimit()))
                .getContent()));
  }

  public Post addPost(Post post) {
    if (validator.isValidPost(post)) {
      return postRepository.save(post);
    }
    return null;
  }

  public Post updatePost(Long id, Post post) {
    Optional<Post> existingPost = postRepository.findById(id);
    System.out.println(existingPost.get());
    if (existingPost.isPresent()) {
      Post existingPostReal = existingPost.get();
      existingPostReal.setTitle(post.getTitle());
      existingPostReal.setBody(post.getBody());
      postRepository.save(existingPostReal);
      return existingPostReal;
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
