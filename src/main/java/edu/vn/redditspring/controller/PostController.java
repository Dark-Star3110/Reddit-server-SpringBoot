package edu.vn.redditspring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.vn.redditspring.model.Post;
import edu.vn.redditspring.payload.PostRequest;
import edu.vn.redditspring.payload.PostResponse;
import edu.vn.redditspring.payload.ResponsePayload;
import edu.vn.redditspring.service.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PostController {
  @Autowired
  private PostService postService;

  @PostMapping("/all-posts")
  public PostResponse getAllPosts(@RequestBody PostRequest postRequest) {
    System.out.println("get all posts");
    return postService.getAllPosts(postRequest, PageRequest.of(postRequest.getCurrentPage(), postRequest.getLimit()));
  }

  @GetMapping("/post/{id}")
  public Post getPost(@PathVariable(name = "id") Long id) {
    System.out.println("get post id: " + id);
    return postService.getPost(id);
  }

  @PostMapping("/add-post")
  public ResponseEntity<ResponsePayload> addPost(@RequestBody Post post) {
    // System.out.println(post.toString());
    boolean check = Optional.ofNullable(postService.addPost(post)).isPresent();
    return check ? ResponseEntity.ok().body(new ResponsePayload(200, true, "add post successfully"))
        : ResponseEntity.status(500).body(new ResponsePayload(500, false, "Server error"));
  }

  @PutMapping("/update-post")
  public ResponseEntity<ResponsePayload> updatePost(@RequestParam(value = "id", required = true) Long id,
      @RequestBody Post post) {
    System.out.println(id);
    System.out.println(post);
    boolean check = Optional.ofNullable(postService.updatePost(id, post)).isPresent();
    return check ? ResponseEntity.status(200).body(new ResponsePayload(200, true, "update post successfully"))
        : ResponseEntity.status(500).body(new ResponsePayload(500, false, "Server error"));
  }

  @DeleteMapping("/delete-post")
  public ResponseEntity<ResponsePayload> deletePost(@RequestParam(value = "id", required = true) Long id) {
    boolean check = postService.deletePost(id);
    return check ? ResponseEntity.status(200).body(new ResponsePayload(200, true, "delete post successfully"))
        : ResponseEntity.status(500).body(new ResponsePayload(500, false, "Server error"));
  }
}
