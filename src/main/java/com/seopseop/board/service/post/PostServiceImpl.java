package com.seopseop.board.service.post;

import com.seopseop.board.DTO.post.PostSaveDTO;
import com.seopseop.board.DTO.post.PostUpdateDTO;
import com.seopseop.board.Exception.NotExistPostException;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.repository.member.MemberRepository;
import com.seopseop.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

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
}
