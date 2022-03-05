package edu.vn.redditspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.vn.redditspring.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
