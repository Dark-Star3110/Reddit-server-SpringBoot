package edu.vn.redditspring.payload;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class PostRequest {
  @Nullable
  private String title;

  @NotNull
  private int limit;

  @NotNull
  private int currentPage;
}
