package edu.vn.redditspring.payload;

import java.util.List;

import edu.vn.redditspring.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse {
  private long totalCount;
  private List<Post> posts;
}
