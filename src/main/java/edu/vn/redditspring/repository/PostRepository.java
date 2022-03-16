package edu.vn.redditspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.vn.redditspring.model.Post;

// cach hard code
// @Repository
// public interface PostRepository extends JpaRepository<Post, Long> {
//   List<Post> findAllByTitleLike(String title, Pageable pageable);

//   long countAllByTitleLike(String title);
// }

//spring jpa dynamic query
@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
}
