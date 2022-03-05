package edu.vn.redditspring.model;

import java.util.Optional;

public class PostValidator {
  public boolean isValidPost(Post post) {
    return Optional.ofNullable(post)
        .filter(p -> p.getTitle().length() > 0)
        .filter(p -> p.getBody().length() > 0)
        .isPresent();
  }
}
