package com.seopseop.board.service.post;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seopseop.board.DTO.post.PostSaveDTO;
import com.seopseop.board.DTO.post.PostUpdateDTO;
import com.seopseop.board.Exception.NotExistPostException;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.member.QMember;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.entity.post.QPost;
import com.seopseop.board.repository.member.MemberRepository;
import com.seopseop.board.repository.post.PostRepository;
import com.seopseop.board.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final JPAQueryFactory queryFactory;



    // post create
    @Override
    @Transactional
    public Long savePost(PostSaveDTO postSaveDTO, String username) {

        Optional<Member> result = memberRepository.findByUsername(username);
        Member member = result.orElseThrow( () -> new NotExistPostException());

        Post savePost = postRepository.save(new Post(postSaveDTO.getTitle(),postSaveDTO.getContent(),member));

        member.increasePostCnt();

        return savePost.getId();
    }

    // post update
    @Override
    @Transactional
    public Long updatePost(PostUpdateDTO postUpdateDTO) {
        Optional<Post> result = postRepository.findById(postUpdateDTO.getId());
        Post post = result.orElseThrow(()-> new NotExistPostException());
        post.updatePost(postUpdateDTO.getTitle(), postUpdateDTO.getContent(),post.getId());
        return post.getId();
    }

    @Override
    @Transactional
    public Post findById(Long post_id) {
        Optional<Post> result = postRepository.findById(post_id);
        Post post = result.orElseThrow(()-> new NotExistPostException());
        return post;
    }

    @Override
    @Transactional
    public Long deletePost(Long post_id, Member writer) {

        Optional<Post> result = postRepository.findById(post_id);
        Post post = result.orElseThrow(()-> new NotExistPostException());

        Optional<Member> result0 = memberRepository.findByUsername(writer.getUsername());
        Member member = result0.orElseThrow(()-> new NotExistPostException());

        post.deletePost();

        member.decreasePostCnt();

        return post.getId();
    }

    @Override
    public Page<Post> findAllActivePostByMember (String username, Pageable pageable) {
        Member mem = memberService.findActiveMemberByUsername(username);

        QMember member = QMember.member;
        QPost post = QPost.post;

        QueryResults<Post> results = queryFactory.selectFrom(post)
                .where(post.post_writer.id.eq(mem.getId()),
                        post.deletedTrue.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetchResults();

        List<Post> posts = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(posts,pageable,total);
    }

//    @Override
//    public Page<Post> findByKeyword(String keyword, Pageable pageable) {
//
//        QPost qpost = QPost.post;
//
//        QueryResults<Post> results = queryFactory.selectFrom(qpost)
//                .where(qpost.title.contains(keyword).or(qpost.content.contains(keyword)),qpost.deletedTrue.eq(false))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//
//        List<Post> posts = results.getResults();
//        long total = results.getTotal();
//
//        return new PageImpl<>(posts,pageable,total);
//    }
}
