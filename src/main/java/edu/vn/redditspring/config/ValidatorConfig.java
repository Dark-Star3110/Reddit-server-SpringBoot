package edu.vn.redditspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.vn.redditspring.model.PostValidator;
import edu.vn.redditspring.model.UserValidator;

@Configuration
public class ValidatorConfig {
  @Bean
  public PostValidator validatePost() {
    return new PostValidator();
  }

  @Bean
  public UserValidator validateUser() {
    return new UserValidator();
  }
}
