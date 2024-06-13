package com.seopseop.board.repository.post;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.entity.post.QPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private JPAQueryFactory queryFactory;
    private QPost post = QPost.post;

    @Autowired
    public PostRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Post> findAllActivePost(Pageable pageable) {
        QueryResults<Post> results = queryFactory
                .selectFrom(post)
                .where(post.deletedTrue.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetchResults();

        List<Post> posts = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(posts,pageable, total);
    }
}
