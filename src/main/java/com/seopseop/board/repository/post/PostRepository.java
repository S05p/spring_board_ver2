package com.seopseop.board.repository.post;

import com.seopseop.board.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom{

    Optional<Post> findById(Long id);

    @Query(value = "SELECT * FROM board.post WHERE MATCH(title, content) AGAINST(:keyword IN BOOLEAN MODE)", nativeQuery = true)
    Page<Post> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
