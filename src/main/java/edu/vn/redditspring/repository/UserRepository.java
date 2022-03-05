package edu.vn.redditspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.vn.redditspring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
