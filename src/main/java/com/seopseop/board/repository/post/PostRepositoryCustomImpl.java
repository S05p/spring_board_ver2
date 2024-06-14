package com.seopseop.board.repository.post;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.member.QMember;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.entity.post.QPost;
import com.seopseop.board.service.member.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final MemberService memberService;
    private final JPAQueryFactory queryFactory;
    private QPost post = QPost.post;
    private QMember member = QMember.member;

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
