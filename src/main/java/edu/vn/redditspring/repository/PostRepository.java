package edu.vn.redditspring.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.vn.redditspring.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByTitleLike(String title, Pageable pageable);

  long countAllByTitleLike(String title);
}
