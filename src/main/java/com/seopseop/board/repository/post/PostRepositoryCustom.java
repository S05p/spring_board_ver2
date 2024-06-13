package com.seopseop.board.repository.post;

import com.seopseop.board.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryCustom {

    Page<Post> findAllActivePost(Pageable pageable);
}
