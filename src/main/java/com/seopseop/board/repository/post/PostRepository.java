package com.seopseop.board.repository.post;

import com.seopseop.board.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findById(Long id);
}
