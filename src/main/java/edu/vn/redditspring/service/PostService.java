package edu.vn.redditspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

  public PostResponse getAllPosts(PostRequest postRequest, Pageable pageable) {
    // hard code
    // return Optional.ofNullable(postRequest.getQuery())
    // .map(value -> new PostResponse(postRepository.countAllByTitleLike(value),
    // postRepository.findAllByTitleLike(value,
    // PageRequest.of(postRequest.getCurrentPage(), postRequest.getLimit()))))
    // .orElseGet(() -> new PostResponse(postRepository.count(),
    // postRepository.findAll(PageRequest.of(postRequest.getCurrentPage(),
    // postRequest.getLimit()))
    // .getContent()));

    // spring jpa dynamic query
    Page<Post> page = postRepository.findAll(new Specification<Post>() {
      @Override
      public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        System.out.println(postRequest.getQuery());
        if (postRequest.getQuery() != null) {
          predicates.add(criteriaBuilder.or(
              criteriaBuilder.like(root.get("title"), "%" + postRequest.getQuery() + "%"),
              criteriaBuilder.like(root.get("body"), "%" + postRequest.getQuery() + "%")));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
      }
    }, pageable);
    return new PostResponse(page.getTotalElements(), page.getContent());
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
