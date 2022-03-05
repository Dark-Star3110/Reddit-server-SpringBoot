package edu.vn.redditspring.model;

import java.util.Optional;

public class UserValidator {
  public boolean isValidUser(User user) {
    return Optional.ofNullable(user)
        .filter(u -> u.getUsername().length() >= 2 && u.getUsername().length() <= 12)
        .isPresent();
  }
}
