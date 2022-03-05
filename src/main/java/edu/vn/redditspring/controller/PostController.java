package edu.vn.redditspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.vn.redditspring.model.Post;
import edu.vn.redditspring.payload.ResponsePayload;
import edu.vn.redditspring.service.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("/all-posts")
  public List<Post> getAllPosts(@RequestParam(value = "limit", required = false) Integer limit) {
    return postService.getAllPosts(limit);
  }

  @PostMapping("/add-todo")
  public ResponseEntity<ResponsePayload> addTodo(@ModelAttribute Post post) {
    boolean check = Optional.ofNullable(postService.addPost(post)).isPresent();
    return check ? ResponseEntity.ok().body(new ResponsePayload(200, true, "add post successfully"))
        : ResponseEntity.status(500).body(new ResponsePayload(500, false, "Server error"));
  }
}
